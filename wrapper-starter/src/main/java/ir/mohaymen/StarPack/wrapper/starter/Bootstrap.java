package ir.mohaymen.starpack.wrapper.starter;

import ir.mohaymen.starpack.wrapper.bdmp.client.BDMPClient;
import ir.mohaymen.starpack.wrapper.facade.BDMPFacadeLayer;

public class Bootstrap {
    private final BDMPClient bdmpClient;

    public Bootstrap(BDMPClient bdmpClient) {
        this.bdmpClient = bdmpClient;
    }
    public BDMPFacadeLayer createClient(){
        return new BDMPFacadeLayer(this.bdmpClient);
    }
}
