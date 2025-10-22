//
//import static org.mockito.Mockito.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import ir.mohaymen.starpack.wrapper.am.client.AMClient;
//import ir.mohaymen.starpack.wrapper.config.ConfigLoader;
//import ir.mohaymen.starpack.wrapper.core.am.CookiesDTO;
//import ir.mohaymen.starpack.wrapper.core.am.LoginRequest;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.*;
//
//import java.net.http.HttpClient;
//import java.net.http.HttpHeaders;
//import java.net.http.HttpResponse;
//import java.util.List;
//import java.util.Map;
//import java.util.Arrays;
//
//public class BlackBoxTest {
//
//    @Mock
//    private HttpClient httpClient;
//
//    @Mock
//    private ConfigLoader configLoader;
//
//    @InjectMocks
//    private AMClient amClient;
//
//    private ObjectMapper objectMapper;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        objectMapper = new ObjectMapper();
//    }
//
//    @Test
//    void testGetCookies() throws Exception {
//        // Arrange
//        String xAuthToken = "mocked-x-auth-token";
//        String cookieSession1 = "mocked-cookie-session1";
//
//        // Mock the configLoader to return mocked values
//        when(configLoader.getString(anyString(), anyString())).thenReturn("mocked-value");
//        when(configLoader.getBoolean(anyBoolean(), anyBoolean())).thenReturn(true);
//        when(configLoader.getInt(anyInt(), anyInt())).thenReturn(1);
//
//        // Mock the HttpResponse to simulate a successful login
//        HttpResponse<String> httpResponse = mock(HttpResponse.class);
//        when(httpResponse.headers()).thenReturn(createMockHeaders(xAuthToken, cookieSession1));
//        when(httpResponse.statusCode()).thenReturn(200);
//        when(httpResponse.body()).thenReturn("mocked-response-body");
//
//        // Mock HttpClient.send to return the mocked response
////        when(httpClient.send(any(), any())).thenReturn(httpResponse);
//
//        // Act
//        CookiesDTO cookiesDTO = amClient.getCookies();
//
//        // Assert
//        assertNotNull(cookiesDTO);
//        assertEquals(xAuthToken, cookiesDTO.getXAuthToken());
//        assertEquals(cookieSession1, cookiesDTO.getSessionToken());
//    }
//
//    @Test
//    void testExtractTokens() throws Exception {
//        // Arrange
//        String xAuthToken = "mocked-x-auth-token";
//        String cookieSession1 = "mocked-cookie-session1";
//
//        // Mock the configLoader to return mocked values
//        when(configLoader.getString(anyString(), anyString())).thenReturn("mocked-value");
//        when(configLoader.getBoolean(anyBoolean(), anyBoolean())).thenReturn(true);
//        when(configLoader.getInt(anyInt(), anyInt())).thenReturn(1);
//
//        // Mock the HttpResponse to simulate a successful login
//        HttpResponse<String> httpResponse = mock(HttpResponse.class);
//        when(httpResponse.headers()).thenReturn(createMockHeaders(xAuthToken, cookieSession1));
//        when(httpResponse.statusCode()).thenReturn(200);
//        when(httpResponse.body()).thenReturn("mocked-response-body");
//
//        // Mock HttpClient.send to return the mocked response
////        when(httpClient.send(any(), any())).thenReturn(httpResponse);
//
//        // Act
//        CookiesDTO cookiesDTO = amClient.getCookies();
//
//        // Assert
//        assertNotNull(cookiesDTO);
//        assertEquals(xAuthToken, cookiesDTO.getXAuthToken());
//        assertEquals(cookieSession1, cookiesDTO.getSessionToken());
//    }
//
//    // Helper method to create mocked HttpHeaders
//    private HttpHeaders createMockHeaders(String xAuthToken, String cookieSession1) {
//        List<String> cookies = Arrays.asList("X-Auth-Token=" + xAuthToken, "cookiesession1=" + cookieSession1);
//        return HttpHeaders.of(
//                Map.of("Set-Cookie", cookies),
//                (key, value) -> true
//                             );
//    }
//}
