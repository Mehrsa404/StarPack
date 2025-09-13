package ir.mohaymen.starpack.wrapper.am.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.mohaymen.starpack.wrapper.config.*;
import ir.mohaymen.starpack.wrapper.core.am.CookiesDTO;
import ir.mohaymen.starpack.wrapper.core.am.LoginRequest;


import java.io.IOException;
import java.net.HttpCookie;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class AMClient {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private final HttpClient httpClient;
    private final String baseUrl;

    public AMClient() {
        this.baseUrl = ConfigLoader.getString("am.service.url", "https://web-star-nta.abriment.mohaymen.ir").trim() ;
        this.httpClient = HttpClient.newBuilder()
                                    .connectTimeout(Duration.ofSeconds(10))
                                    .followRedirects(HttpClient.Redirect.NEVER)
                                    .build();
    }

    private CookiesDTO extractTokens(List<LoginRequest> body) throws Exception {

        HttpResponse<String> response = LoginAndGetResponse(body);

        String xAuthCookie = response.headers()
                                     .allValues("Set-Cookie")
                                     .stream()
                                     .flatMap(h -> HttpCookie.parse(h).stream())
                                     .filter(c -> c.getName().equalsIgnoreCase("X-Auth-Token"))
                                     .map(HttpCookie::getValue)
                                     .findFirst()
                                     .orElse(null);

        String cookiesession1 = response.headers()
                                        .allValues("Set-Cookie")
                                        .stream()
                                        .flatMap(h -> HttpCookie.parse(h).stream())
                                        .filter(c -> c.getName().equalsIgnoreCase("cookiesession1"))
                                        .map(HttpCookie::getValue)
                                        .findFirst()
                                        .orElse(null);
        CookiesDTO cookiesDTO = new CookiesDTO(xAuthCookie ,  cookiesession1);
        return cookiesDTO;
    }

    private HttpResponse<String> LoginAndGetResponse(List<LoginRequest> body) throws URISyntaxException, IOException, InterruptedException {
        String query = String.format("Mrpc-EngineName=%s&Mrpc-EngineVersion=%s&W_MultiTech=%s&W_InputAsArray=%s&W_HandlingMode=%d",
                                     URLEncoder.encode(ConfigLoader.getString("am.service.enginName", "AM"), StandardCharsets.UTF_8),
                                     URLEncoder.encode(ConfigLoader.getString("am.service.enginVersion", "403.1.1493.0"), StandardCharsets.UTF_8),
                                     ConfigLoader.getBoolean("am.service.multiTech", false), ConfigLoader.getBoolean("am.service.inputAsArray", true), ConfigLoader.getInt("am.service.handlingMode", 1));

        URI uri = new URI(baseUrl + "/gateway/MSSE.AM/api/IdentityManagementApi/Login?" + query);

        String jsonBody = MAPPER.writeValueAsString(body);

        HttpRequest request = HttpRequest.newBuilder(uri)
                                         .timeout(Duration.ofSeconds(15))
                                         .header("Accept", "application/json, text/plain, */*")
                                         .header("Content-Type", "application/json")
                                         .header("X-Mrpc-ExecNetTimeout", "6000")
                                         .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                                         .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() / 100 != 2) {
            throw new RuntimeException("Login failed, status=" + response.statusCode() + ", body=" + response.body());
        }
        return response;
    }

    public CookiesDTO getCookies() throws Exception {
        LoginRequest req = new LoginRequest(ConfigLoader.getString("am.service.user", "admin"), ConfigLoader.getString("am.service.password", "123"));
        return extractTokens(List.of(req));
    }

}
