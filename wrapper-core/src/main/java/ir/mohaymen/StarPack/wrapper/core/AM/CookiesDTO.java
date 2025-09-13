package ir.mohaymen.starpack.wrapper.core.am;

public class CookiesDTO {
    private String sessionToken;
    private String XAuthToken;

    public CookiesDTO(String XAuthToken, String sessionToken) {
        this.sessionToken = sessionToken;
        this.XAuthToken = XAuthToken;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public String getXAuthToken() {
        return XAuthToken;
    }

    public void setXAuthToken(String XAuthToken) {
        this.XAuthToken = XAuthToken;
    }
}
