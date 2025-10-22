package ir.mohaymen.starpack.wrapper.core.am;

public class AmRequestParams {
    private final String amEngineName;
    private final String amEngineVersion;
    private final boolean amInputAsArray;
    private final boolean amMultitech;
    private final String amUsername;
    private final String amPassword;
    private final String amUrl;
    private final Integer amHandlingMode;

    public AmRequestParams(String amEngineName,
                           String amEngineVersion,
                           boolean amInputAsArray,
                           boolean amMultitech,
                           String amUsername,
                           String amPassword,
                           String amUrl,
                           Integer amHandlingMode) {
        this.amEngineName = amEngineName;
        this.amEngineVersion = amEngineVersion;
        this.amInputAsArray = amInputAsArray;
        this.amMultitech = amMultitech;
        this.amUsername = amUsername;
        this.amPassword = amPassword;
        this.amUrl = amUrl;
        this.amHandlingMode = amHandlingMode;
    }

    public String getAmEngineName() {
        return amEngineName;
    }

    public String getAmEngineVersion() {
        return amEngineVersion;
    }

    public boolean isAmInputAsArray() {
        return amInputAsArray;
    }

    public boolean isAmMultitech() {
        return amMultitech;
    }

    public String getAmUsername() {
        return amUsername;
    }

    public String getAmPassword() {
        return amPassword;
    }

    public String getAmUrl() {
        return amUrl;
    }

    public Integer getAmHandlingMode() {
        return amHandlingMode;
    }
}
