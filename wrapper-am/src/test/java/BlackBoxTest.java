import static org.junit.jupiter.api.Assertions.*;

import ir.mohaymen.starpack.wrapper.am.client.AMClient;
import ir.mohaymen.starpack.wrapper.core.am.CookiesDTO;
import org.junit.jupiter.api.Test;

public class BlackBoxTest {
    @Test
    void testAuthMethod() throws Exception {
        AMClient client = new AMClient();
        CookiesDTO cookies = client.getCookies();
        System.out.println(cookies.getXAuthToken());
        System.out.println(cookies.getSessionToken());
        assertNotNull(cookies.getSessionToken());
        assertNotNull(cookies.getXAuthToken());
    }
}
