package ir.mohaymen.starpack.wrapper.bdmp.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.mohaymen.starpack.wrapper.am.client.AMClient;
import ir.mohaymen.starpack.wrapper.core.bdmp.get_Simorgh_rows.GetSimorghRowsInputDTO;
import ir.mohaymen.starpack.wrapper.config.ConfigLoader;
import ir.mohaymen.starpack.wrapper.core.bdmp.get_Simorgh_rows.GetSimorghRowsOutputDTO;
import ir.mohaymen.starpack.wrapper.core.bdmp.get_warehouses.GetWarhousesType;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BDMPClient {
    private final static AMClient AM_CLIENT = new AMClient();
    private final static String X_AuthToken;

    static {
        try {
            X_AuthToken = AM_CLIENT.getCookies().getXAuthToken();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private final static String sessionCookie;

    static {
        try {
            sessionCookie = AM_CLIENT.getCookies().getSessionToken();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private final String baseUrl;
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private final HttpClient httpClient;


    public BDMPClient() {
        this.baseUrl = ConfigLoader.getString("bdmp.service.url", "https://web-star-nta.abriment.mohaymen.ir");
        this.httpClient = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10)).followRedirects(HttpClient.Redirect.NEVER).build();
    }

    private JsonNode getSimorghRows(int warehouseId,
                                    String jsonFilter,
                                    Integer fromPage,
                                    Integer toPage,
                                    List<Integer> visibleColumnIds) throws Exception {

        int finalFromPage = (fromPage != null) ? fromPage : ConfigLoader.getInt("bdmp.service.showSimorghRowsFromPage", 0);

        int finalToPage = (toPage != null) ? toPage : ConfigLoader.getInt("bdmp.service.showSimorghRowsToPage", 20);

        GetSimorghRowsInputDTO dto = new GetSimorghRowsInputDTO(warehouseId, finalFromPage, finalToPage);
        String query = String.join("&",
                                   qp("Mrpc-EngineName", ConfigLoader.getString("bdmp.service.engineName", "LADW")),
                                   qp("Mrpc-EngineVersion", ConfigLoader.getString("bdmp.service.engineVersion", "403.1.1508.0")),
                                   qp("W_MultiTech", ConfigLoader.getBoolean("bdmp.service.multiTech", false)),
                                   qp("W_InputAsArray", ConfigLoader.getBoolean("bdmp.service.inputAsArray", true)),
                                   qp("W_HandlingMode", ConfigLoader.getInt("bdmp.service.handlingMode", 1)));

        URI uri = new URI(baseUrl + "/gateway/MSSE.LADW/api/SimorghExplorerApi/GetSimorghRows?" + query);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode filterNode;
        if (jsonFilter != null) {
            filterNode = mapper.readTree(jsonFilter);
        } else {
            filterNode = null;
        }

        Map<String, Object> requestDto = new HashMap<>();
        requestDto.put("WarehouseId", dto.getWarehouseId());
        requestDto.put("ColumnIds", visibleColumnIds); // مقدار null مجاز است در HashMap

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

        HttpRequest request = HttpRequest.newBuilder(uri)
                                         .timeout(Duration.ofSeconds(15))
                                         .header("Accept", "application/json, text/plain, */*")
                                         .header("Content-Type", "application/json")
                                         .header("Cookie", "X-Auth-Token=" + X_AuthToken)
                                         .POST(HttpRequest.BodyPublishers.ofString(body))
                                         .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() / 100 != 2) {
            throw new RuntimeException("Getting simorgh rows failed, status=" + response.statusCode() + ", body=" + response.body());
        }
        return MAPPER.readTree(response.body());
    }


    private JsonNode getSimorghWarehouses() throws Exception {
        String query = String.join("&",
                                   qp("Mrpc-EngineName", ConfigLoader.getString("bdmp.service.engineName", "LADW")),
                                   qp("Mrpc-EngineVersion", ConfigLoader.getString("bdmp.service.engineVersion", "403.1.1508.0")),
                                   qp("W_MultiTech", ConfigLoader.getBoolean("bdmp.service.multiTech", false)),
                                   qp("W_InputAsArray", ConfigLoader.getBoolean("bdmp.service.inputAsArray", true)),
                                   qp("W_HandlingMode", ConfigLoader.getInt("bdmp.service.handlingMode", 1)));

        URI uri = new URI(baseUrl + "/gateway/MSSE.LADW/api/WarehouseEditManagementApi/GetWarehouses?" + query);

        String cookieHeader = "cookiesession1=" + sessionCookie + ";X-Auth-Token=" + X_AuthToken;
        HttpRequest request = HttpRequest.newBuilder(uri)
                                         .timeout(Duration.ofSeconds(15))
                                         .header("Accept", "application/json, text/plain, */*")
                                         .header("Content-Type", "application/json")
                                         .header("Cookie", cookieHeader)
                                         .POST(HttpRequest.BodyPublishers.ofString("[]"))
                                         .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.body() != null && !response.body().equals("[]")) {
            return MAPPER.readTree(response.body());
        }
        return null;
    }


    private static String qp(String key, Object value) {
        return URLEncoder.encode(key, StandardCharsets.UTF_8) + "=" + URLEncoder.encode(String.valueOf(value), StandardCharsets.UTF_8);
    }

    public GetSimorghRowsOutputDTO getStructuredRecordSourceRows(int warehouseId,
                                                                 String jsonFilter,
                                                                 Integer fromPage,
                                                                 Integer toPage,
                                                                 List<Integer> columnIds) throws Exception {
        var response =
                MAPPER.readValue(getSimorghRows(warehouseId, jsonFilter, fromPage, toPage, columnIds).toString(), GetSimorghRowsOutputDTO.class);
        return response;
    }

    public GetWarhousesType getWarehousesStructured() throws Exception {
        GetWarhousesType output = MAPPER.readValue(getSimorghWarehouses().toString(), GetWarhousesType.class);
        return output;
    }

}


