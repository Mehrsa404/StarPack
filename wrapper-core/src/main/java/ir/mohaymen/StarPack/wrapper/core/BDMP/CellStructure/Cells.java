package ir.mohaymen.StarPack.wrapper.core.BDMP.CellStructure;

import java.util.HashMap;
import java.util.List;

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
