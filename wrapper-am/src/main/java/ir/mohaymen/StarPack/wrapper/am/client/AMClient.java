package ir.mohaymen.StarPack.wrapper.am.client;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.mohaymen.StarPack.wrapper.config.*;
import ir.mohaymen.StarPack.wrapper.core.domain.AM.CookiesDTO;
import ir.mohaymen.StarPack.wrapper.core.domain.AM.LoginRequest;


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
        this.baseUrl = ConfigLoader.getString("am.service.url").trim() ;
        this.httpClient = HttpClient.newBuilder()
                                    .connectTimeout(Duration.ofSeconds(10))
                                    .followRedirects(HttpClient.Redirect.NEVER)
                                    .build();
    }

    public CookiesDTO getTokens(List<LoginRequest> body) throws Exception {

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
        assert xAuthCookie != null;
        assert cookiesession1 != null;
        CookiesDTO cookiesDTO = new CookiesDTO(xAuthCookie ,  cookiesession1);
        return cookiesDTO;
    }

    private HttpResponse<String> LoginAndGetResponse(List<LoginRequest> body) throws URISyntaxException, IOException, InterruptedException {
        String query = String.format("Mrpc-EngineName=%s&Mrpc-EngineVersion=%s&W_MultiTech=%s&W_InputAsArray=%s&W_HandlingMode=%d",
                                     URLEncoder.encode(ConfigLoader.getString("am.service.enginName"), StandardCharsets.UTF_8),
                                     URLEncoder.encode(ConfigLoader.getString("am.service.enginVersion"), StandardCharsets.UTF_8),
                                     ConfigLoader.getBoolean("am.service.multiTech"), ConfigLoader.getBoolean("am.service.inputAsArray"), ConfigLoader.getInt("am.service.handlingMode"));

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
        LoginRequest req = new LoginRequest(ConfigLoader.getString("am.service.user"), ConfigLoader.getString("am.service.password"));
        return getTokens(List.of(req));
    }

    public static void main(String[] args) throws Exception {
        AMClient client = new AMClient();
        CookiesDTO cookiesDTO = client.getCookies();
        System.out.println(cookiesDTO.getSessionToken());
        System.out.println(cookiesDTO.getXAuthToken());
    }
}
