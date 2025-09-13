import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ir.mohaymen.starpack.wrapper.bdmp.client.BDMPClient;
import ir.mohaymen.starpack.wrapper.core.bdmp.get_Simorgh_rows.GetSimorghRowsOutputDTO;
import ir.mohaymen.starpack.wrapper.core.bdmp.get_warehouses.GetWarhousesType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BlackBoxTest {
    @Test
    void testGetWarehouses() throws Exception {
        BDMPClient bdmpClient = new BDMPClient();
        GetWarhousesType warehousesType = bdmpClient.getWarehousesStructured();
        ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        System.out.println(objectMapper.writeValueAsString(warehousesType));
        assertNotNull(warehousesType);
    }

    @Test
    void testGetSimorghRows() throws Exception {
        BDMPClient bdmpClient = new BDMPClient();
////                String jsonFilter = """
////                {
////                  "LeafClause": {
////                    "ColumnId": 155,
////                    "QueryType": 13,
////                    "Value": {
////                      "ClauseValueType": 0,
////                      "Value": "2/202"
////                    }
////                  },
////                  "Not": false
////                }""";
//
        String jsonFilter = """
                {
                  "LeafClause": {
                    "ColumnId": 153,
                    "QueryType": 8,
                    "Value": {
                      "ClauseValueType": 0,
                      "Value": "1"
                    }
                  },
                  "Not": false
                }""";
        GetSimorghRowsOutputDTO output = bdmpClient.getStructuredRecordSourceRows(24, jsonFilter, null , 6);
        assertNotNull(output);
    }
}
