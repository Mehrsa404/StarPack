package ir.mohaymen.starpack.wrapper.bdmp.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.mohaymen.starpack.wrapper.am.client.AMClient;
import ir.mohaymen.starpack.wrapper.core.bdmp.BdmpRequestParams;
import ir.mohaymen.starpack.wrapper.core.bdmp.get_Simorgh_rows.*;
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
import java.util.Objects;

public class BDMPClient {
    private final AMClient amClient;
    private final BdmpRequestParams bdmpRequestParams;
    private final HttpClient client;
    private final ObjectMapper objectMapper;
    private final ConfigLoader configLoader;

    public BDMPClient(AMClient amClient, BdmpRequestParams bdmpRequestParams, HttpClient client, ObjectMapper objectMapper, ConfigLoader configLoader) {
        this.amClient = amClient;
        this.bdmpRequestParams = bdmpRequestParams;
        this.client = client;
        this.objectMapper = objectMapper;
        this.configLoader = configLoader;
    }

    private JsonNode getSimorghRows(int warehouseId,
                                    String jsonFilter,
                                    Integer fromPage,
                                    Integer toPage,
                                    List<Integer> visibleColumnIds) throws Exception {
        String X_AuthToken = this.amClient.getCookies().getXAuthToken();
        int finalFromPage = this.configLoader.getInt(fromPage, 0);

        int finalToPage = this.configLoader.getInt(toPage, 20);

        GetSimorghRowsInputDTO dto = new GetSimorghRowsInputDTO(warehouseId, finalFromPage, finalToPage);
        String query = String.join("&",
                                   qp("Mrpc-EngineName", this.configLoader.getString(this.bdmpRequestParams.getBdmpEngineName(), "LADW")),
                                   qp("Mrpc-EngineVersion", this.configLoader.getString(this.bdmpRequestParams.getBdmpEngineVersion(), "403.1.1508.0")),
                                   qp("W_MultiTech", this.configLoader.getBoolean(this.bdmpRequestParams.isBdmpMultitech(), false)),
                                   qp("W_InputAsArray", this.configLoader.getBoolean(this.bdmpRequestParams.isBdmpInputAsArray(), true)),
                                   qp("W_HandlingMode", this.configLoader.getInt(bdmpRequestParams.getBdmpHandlingMode(), 1)));

        URI uri = new URI(this.configLoader.getString(this.bdmpRequestParams.getBdmpUrl(), "https://web-star-nta.abriment.mohaymen.ir") + "/gateway/MSSE.LADW/api/SimorghExplorerApi/GetSimorghRows?" + query);
        System.out.println(uri);

        JsonNode filterNode;
        if (jsonFilter != null) {
            filterNode = this.objectMapper.readTree(jsonFilter);
        } else {
            filterNode = null;
        }

        Map<String, Object> requestDto = new HashMap<>();
        requestDto.put("WarehouseId", dto.getWarehouseId());
        requestDto.put("ColumnIds", visibleColumnIds);

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
        String body = this.objectMapper.writeValueAsString(root);

        HttpRequest request = HttpRequest.newBuilder(uri)
                                         .timeout(Duration.ofSeconds(15))
                                         .header("Accept", "application/json, text/plain, */*")
                                         .header("Content-Type", "application/json")
                                         .header("Cookie", "X-Auth-Token=" + X_AuthToken)
                                         .POST(HttpRequest.BodyPublishers.ofString(body))
                                         .build();

        HttpResponse<String> response = this.client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() / 100 != 2) {
            throw new RuntimeException("Getting simorgh rows failed, status=" + response.statusCode() + ", body=" + response.body());
        }
        return this.objectMapper.readTree(response.body());
    }


    private JsonNode getSimorghWarehouses() throws Exception {
        String X_AuthToken = this.amClient.getCookies().getXAuthToken();
        String sessionCookie = this.amClient.getCookies().getXAuthToken();
        String query = String.join("&",
                                   qp("Mrpc-EngineName", this.configLoader.getString(this.bdmpRequestParams.getBdmpEngineName(), "LADW")),
                                   qp("Mrpc-EngineVersion", this.configLoader.getString(this.bdmpRequestParams.getBdmpEngineVersion(), "403.1.1508.0")),
                                   qp("W_MultiTech", this.configLoader.getBoolean(this.bdmpRequestParams.isBdmpMultitech(), false)),
                                   qp("W_InputAsArray", this.configLoader.getBoolean(this.bdmpRequestParams.isBdmpInputAsArray(), true)),
                                   qp("W_HandlingMode", this.configLoader.getInt(bdmpRequestParams.getBdmpHandlingMode(), 1)));

        URI uri = new URI(this.configLoader.getString(this.bdmpRequestParams.getBdmpUrl(), "https://web-star-nta.abriment.mohaymen.ir") + "/gateway/MSSE.LADW/api/WarehouseEditManagementApi/GetWarehouses?" + query);

        String cookieHeader = "cookiesession1=" + sessionCookie + ";X-Auth-Token=" + X_AuthToken;
        HttpRequest request = HttpRequest.newBuilder(uri)
                                         .timeout(Duration.ofSeconds(15))
                                         .header("Accept", "application/json, text/plain, */*")
                                         .header("Content-Type", "application/json")
                                         .header("Cookie", cookieHeader)
                                         .POST(HttpRequest.BodyPublishers.ofString("[]"))
                                         .build();

        HttpResponse<String> response = this.client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() / 100 != 2) {
            throw new RuntimeException("Getting simorgh rows failed, status=" + response.statusCode() + ", body=" + response.body());
        }
        if (response.body() != null && !response.body().equals("[]")) {
            return this.objectMapper.readTree(response.body());
        }
        return null;
    }


    private String qp(String key, Object value) {
        return URLEncoder.encode(key, StandardCharsets.UTF_8) + "=" + URLEncoder.encode(String.valueOf(value), StandardCharsets.UTF_8);
    }

    public GetSimorghRowsOutputDTO getStructuredRecordSourceRows(int warehouseId,
                                                                 String jsonFilter,
                                                                 Integer fromPage,
                                                                 Integer toPage,
                                                                 List<Integer> columnIds) throws Exception {
        return this.objectMapper.readValue(getSimorghRows(warehouseId, jsonFilter, fromPage, toPage, columnIds).toString(), GetSimorghRowsOutputDTO.class);
    }

    public GetWarhousesType getWarehousesStructured() throws Exception {
        return this.objectMapper.readValue(Objects.requireNonNull(getSimorghWarehouses()).toString(), GetWarhousesType.class);
    }
}


