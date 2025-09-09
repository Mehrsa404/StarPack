import ir.mohaymen.StarPack.wrapper.facade.BDMP_FacadeLayer;

public class Bootstrap {
    public static BDMP_FacadeLayer createClient(){
        return new BDMP_FacadeLayer();
    }
}
