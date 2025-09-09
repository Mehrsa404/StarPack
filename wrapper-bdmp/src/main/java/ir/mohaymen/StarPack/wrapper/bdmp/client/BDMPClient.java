package ir.mohaymen.StarPack.wrapper.bdmp.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.mohaymen.StarPack.wrapper.am.client.AMClient;
import ir.mohaymen.StarPack.wrapper.core.domain.BDMP.GetSimorghRowsInputDTO;
import ir.mohaymen.StarPack.wrapper.config.ConfigLoader;
import ir.mohaymen.StarPack.wrapper.core.domain.BDMP.GetSimorghRowsOutputDTO;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.List;
import java.util.Map;

public class BDMPClient {
    private final static AMClient AM_CLIENT = new AMClient();
    private final String baseUrl;
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private final HttpClient httpClient;
    private final GetSimorghRowsInputDTO getSimorghRowsInputDTO = new GetSimorghRowsInputDTO(ConfigLoader.getInt("bdmp.service.warehouseId"), ConfigLoader.getInt("bdmp.service.showSimorghRowsFromPage", 0), ConfigLoader.getInt("bdmp.service.showSimorghRowsToPage", 100));


    public BDMPClient() {
        this.baseUrl = ConfigLoader.getString("bdmp.service.url");
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .followRedirects(HttpClient.Redirect.NEVER)
                .build();
    }

    private JsonNode getSimorghRows(int warehouseId, String jsonFilter) throws Exception {

        GetSimorghRowsInputDTO dto = new GetSimorghRowsInputDTO(
                warehouseId,
                ConfigLoader.getInt("bdmp.service.showSimorghRowsFromPage",0),
                ConfigLoader.getInt("bdmp.service.showSimorghRowsToPage"
        ));
        String query = String.join("&",
                                   qp("Mrpc-EngineName",  ConfigLoader.getString("bdmp.service.engineName")),
                                   qp("Mrpc-EngineVersion", ConfigLoader.getString("bdmp.service.engineVersion")),
                                   qp("W_MultiTech",   ConfigLoader.getBoolean("bdmp.service.multiTech")),
                                   qp("W_InputAsArray",ConfigLoader.getBoolean("bdmp.service.inputAsArray")),
                                   qp("W_HandlingMode",ConfigLoader.getInt("bdmp.service.handlingMode", 1))
                                  );

        URI uri = new URI(baseUrl + "/gateway/MSSE.LADW/api/SimorghExplorerApi/GetSimorghRows?" + query);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode filterNode = mapper.readTree(jsonFilter);

        Map<String, Object> requestDto = Map.of(
                "WarehouseId", dto.getWarehouseId()
                                               );

        var paging = new java.util.LinkedHashMap<String, Object>();
        paging.put("PagingType", "FROM_SIZE");
        paging.put("From", dto.getFromPage());
        paging.put("Size", dto.getToPage());

        var clause = new java.util.LinkedHashMap<String, Object>();
        clause.put("SimorghExplorerRequestDtos", List.of(requestDto));
        clause.put("Filter", filterNode);
        clause.put("PagingInfo", paging);

        var root = new java.util.ArrayList<>();
        root.add(clause);
        root.add(null);
        String body = MAPPER.writeValueAsString(root);

        String xAuthToken = AM_CLIENT.getCookies().getXAuthToken();
        HttpRequest request = HttpRequest.newBuilder(uri)
                                         .timeout(Duration.ofSeconds(15))
                                         .header("Accept", "application/json, text/plain, */*")
                                         .header("Content-Type", "application/json")
                                         .header("Cookie", "X-Auth-Token=" + xAuthToken) // طبق داک
                                         .POST(HttpRequest.BodyPublishers.ofString(body))
                                         .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() / 100 != 2) {
            throw new RuntimeException("Getting simorgh rows failed, status=" + response.statusCode() + ", body=" + response.body());
        }
        System.out.println(response.body());
        return MAPPER.readTree(response.body());
    }


    public void getSimorghWarehouses() throws Exception {

    }

    private static String qp(String key, Object value) {
        return URLEncoder.encode(key, StandardCharsets.UTF_8)
               + "="
               + URLEncoder.encode(String.valueOf(value), StandardCharsets.UTF_8);
    }

    public GetSimorghRowsOutputDTO getRecordSourceRows(int warehouseId, String jsonFilter) throws Exception {
        var response = MAPPER.readValue(getSimorghRows(warehouseId, jsonFilter).toString(), GetSimorghRowsOutputDTO.class);
        return response;
    }

    public static void main(String[] args) throws Exception {
        BDMPClient bdmpClient = new BDMPClient();

//                String jsonFilter = """
//                {
//                  "LeafClause": {
//                    "ColumnId": 155,
//                    "QueryType": 13,
//                    "Value": {
//                      "ClauseValueType": 0,
//                      "Value": "2/202"
//                    }
//                  },
//                  "Not": false
//                }""";

//        String jsonFilter = """
//                {
//                  "LeafClause": {
//                    "ColumnId": 153,
//                    "QueryType": 8,
//                    "Value": {
//                      "ClauseValueType": 0,
//                      "Value": "1"
//                    }
//                  },
//                  "Not": false
//                }""";
        String jsonFilter = "";
        System.out.println(bdmpClient.getRecordSourceRows(24,jsonFilter).toString());
    }
}


