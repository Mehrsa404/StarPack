package ir.mohaymen.StarPack.wrapper.core.AM;

import java.util.List;

public record LoginDTO(Client clien, String Password, String Username, String Nonce, String CaptchaId, String CaptchaInputValue) {
    public record Client(String ClientId, List<String> redirectUrls, List<String> Scopes) {
    }

}