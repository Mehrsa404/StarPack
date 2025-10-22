package ir.mohaymen.starpack.wrapper.core.bdmp;

public class BdmpRequestParams {

    private final String bdmpUrl;
    private final Integer bdmpHandlingMode;
    private final String bdmpEngineName;
    private final String bdmpEngineVersion;
    private final boolean bdmpInputAsArray;
    private final boolean bdmpMultitech;


    public BdmpRequestParams(String bdmpEngineName,
                             String bdmpEngineVersion,
                             boolean bdmpInputAsArray,
                             boolean bdmpMultitech,
                             String bdmpUrl,
                             Integer bdmpHandlingMode) {
        this.bdmpEngineName = bdmpEngineName;
        this.bdmpEngineVersion = bdmpEngineVersion;
        this.bdmpInputAsArray = bdmpInputAsArray;
        this.bdmpMultitech = bdmpMultitech;
        this.bdmpUrl = bdmpUrl;
        this.bdmpHandlingMode = bdmpHandlingMode;
    }

    public String getBdmpUrl() {
        return bdmpUrl;
    }

    public Integer getBdmpHandlingMode() {
        return bdmpHandlingMode;
    }

    public String getBdmpEngineName() {
        return bdmpEngineName;
    }

    public String getBdmpEngineVersion() {
        return bdmpEngineVersion;
    }

    public boolean isBdmpInputAsArray() {
        return bdmpInputAsArray;
    }

    public boolean isBdmpMultitech() {
        return bdmpMultitech;
    }

}
