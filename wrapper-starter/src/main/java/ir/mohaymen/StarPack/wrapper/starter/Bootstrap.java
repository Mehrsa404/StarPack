package ir.mohaymen.starpack.wrapper.starter;

public class Bootstrap {
    public static ir.mohaymen.starpack.wrapper.facade.BDMPFacadeLayer createClient(){
        return new ir.mohaymen.starpack.wrapper.facade.BDMPFacadeLayer();
    }
}
