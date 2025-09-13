package ir.mohaymen.starpack.wrapper.core.bdmp.cell_structure;

import java.util.HashMap;
import java.util.List;

public class Cells {
    private Sid sid;
    private List<Cell> cells;
    private HashMap<Sid, List<Cell>> cellsMap = new HashMap<>();

    public void addRow(Sid sid, List<Cell> cells) {
        this.cells = cells;
        this.sid = sid;
        cellsMap.put( sid, cells );
    }


}
