import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ir.mohaymen.starpack.wrapper.bdmp.client.BDMPClient;
import ir.mohaymen.starpack.wrapper.bdmp.filter.*;
import ir.mohaymen.starpack.wrapper.core.bdmp.get_Simorgh_rows.GetSimorghRowsOutputDTO;
import ir.mohaymen.starpack.wrapper.core.bdmp.get_warehouses.GetWarhousesType;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

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
    void testGetSimorghRowsMethod() throws Exception {
        BDMPClient bdmpClient = new BDMPClient();

        int clauseValueType = 0;

        LeafClause c1 = new LeafClause(292, QueryType.Equal, new ValueClause(clauseValueType, "3"));

        LeafClause c2 = new LeafClause(296, QueryType.Equal, new ValueClause(clauseValueType, "140301"));

        LeafClause c3 = new LeafClause(290, QueryType.Equal, new ValueClause(clauseValueType, "2952094385"));

        Filter filter = new Filter();
        filter.setAnd(Arrays.asList(new FilterItem(c1), new FilterItem(c2), new FilterItem(c3)));
        filter.setNot(false);

        ObjectMapper om = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

        String json = om.writeValueAsString(filter);
        System.out.println(json);
        GetSimorghRowsOutputDTO output = bdmpClient.getStructuredRecordSourceRows(47, json, 0, 10);
        ObjectMapper MAPPER = new ObjectMapper();
        System.out.println(MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(output.getDataList()));
        assertNotNull(output);
    }
}
