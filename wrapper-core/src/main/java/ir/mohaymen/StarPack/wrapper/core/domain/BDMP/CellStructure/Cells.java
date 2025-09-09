package ir.mohaymen.StarPack.wrapper.core.domain.BDMP.CellStructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cells {
    private SID sid;
    private List<Cell> cells;
    private HashMap<SID, List<Cell>> cellsMap = new HashMap<>();

    public void addRow(SID sid, List<Cell> cells) {
        this.cells = cells;
        this.sid = sid;
        cellsMap.put( sid, cells );
    }


}
