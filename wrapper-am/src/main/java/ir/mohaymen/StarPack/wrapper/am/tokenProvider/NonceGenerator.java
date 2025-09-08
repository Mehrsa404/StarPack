package ir.mohaymen.StarPack.wrapper.am.tokenProvider;

import java.security.SecureRandom;
import java.util.Base64;

public class NonceGenerator {

    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder().withoutPadding();

    public static String generate(int size) {
        byte[] bytes = new byte[size];
        secureRandom.nextBytes(bytes);
        return base64Encoder.encodeToString(bytes);
    }


    public static String generate() {
        return generate(16);
    }
}
