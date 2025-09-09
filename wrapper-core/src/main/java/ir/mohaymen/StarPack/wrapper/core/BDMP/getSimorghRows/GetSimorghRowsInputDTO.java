package ir.mohaymen.StarPack.wrapper.core.BDMP.getSimorghRows;


public class GetSimorghRowsInputDTO {
    private int warehouseId;
    private int FromPage;
    private int ToPage;

    public GetSimorghRowsInputDTO(int warehouseId, int fromPage, int toPage) {
        this.warehouseId = warehouseId;
        FromPage = fromPage;
        ToPage = toPage;
    }

    public int getToPage() {
        return ToPage;
    }

    public void setToPage(int toPage) {
        ToPage = toPage;
    }

    public int getFromPage() {
        return FromPage;
    }

    public void setFromPage(int fromPage) {
        FromPage = fromPage;
    }

    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }


}
