package ir.mohaymen.StarPack.wrapper.facade;

import ir.mohaymen.StarPack.wrapper.bdmp.client.BDMPClient;
import ir.mohaymen.StarPack.wrapper.core.BDMP.getSimorghRows.GetSimorghRowsOutputDTO;
import ir.mohaymen.StarPack.wrapper.core.BDMP.getWarehouses.GetWarhousesType;

public class BDMP_FacadeLayer {
    private static BDMPClient bdmpClient = new BDMPClient();

    public static GetSimorghRowsOutputDTO getSimorghRows(int warehouseId, String jsonFilter) throws Exception {
        return bdmpClient.getStructuredRecordSourceRows(warehouseId, jsonFilter);
    }

    public static GetWarhousesType GetSimorghWarehouses() throws Exception {
        return bdmpClient.getWarehousesStructured();
    }
}
