package ir.mohaymen.starpack.wrapper.facade;

import ir.mohaymen.starpack.wrapper.bdmp.client.BDMPClient;
import ir.mohaymen.starpack.wrapper.core.bdmp.get_Simorgh_rows.GetSimorghRowsOutputDTO;
import ir.mohaymen.starpack.wrapper.core.bdmp.get_warehouses.GetWarhousesType;

import java.util.List;

public class BDMPFacadeLayer {
    private static BDMPClient bdmpClient = new BDMPClient();

    public GetSimorghRowsOutputDTO getSimorghRows(int warehouseId,
                                                  String jsonFilter,
                                                  Integer fromPage,
                                                  Integer toPage,
                                                  List<Integer> columnIds) throws Exception {
        return bdmpClient.getStructuredRecordSourceRows(warehouseId, jsonFilter, fromPage, toPage, columnIds);
    }

    public GetWarhousesType getSimorghWarehouses() throws Exception {
        return bdmpClient.getWarehousesStructured();
    }
}
