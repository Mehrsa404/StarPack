package ir.mohaymen.starpack.wrapper.core.bdmp.get_Simorgh_rows;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetSimorghRowsOutputDTO {

    @JsonProperty("DataList")
    private List<RowItem> dataList;

    @JsonProperty("FoundItemsCount")
    private Long foundItemsCount;

    @JsonProperty("Took")
    private Long took;

    @JsonProperty("Page")
    private Object page;

    @JsonProperty("WarningMessage")
    private String warningMessage;

    public List<RowItem> getDataList() {
        return dataList;
    }

    public void setDataList(List<RowItem> dataList) {
        this.dataList = dataList;
    }

    public Long getFoundItemsCount() {
        return foundItemsCount;
    }

    public void setFoundItemsCount(Long foundItemsCount) {
        this.foundItemsCount = foundItemsCount;
    }

    public Long getTook() {
        return took;
    }

    public void setTook(Long took) {
        this.took = took;
    }

    public Object getPage() {
        return page;
    }

    public void setPage(Object page) {
        this.page = page;
    }

    public String getWarningMessage() {
        return warningMessage;
    }

    public void setWarningMessage(String warningMessage) {
        this.warningMessage = warningMessage;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class RowItem {
        @JsonProperty("WarehouseId")
        private Integer warehouseId;

        @JsonProperty("ImportId")
        private Integer importId;

        @JsonProperty("CreationDate")
        private String creationDate;

        @JsonIgnore
        @JsonProperty("MatchedQueryNames")
        private List<String> matchedQueryNames;

        @JsonIgnore
        @JsonProperty("DistributedId")
        private Integer distributedId;

        @JsonIgnore
        @JsonProperty("SId")
        private String sId;

        @JsonProperty("Cells")
        private List<Cell> cells;

        @JsonProperty("BinaryMetadata")
        private List<BinaryMetadata> binaryMetadata;

        @JsonProperty("Score")
        private Number score;

        public Integer getWarehouseId() {
            return warehouseId;
        }

        public void setWarehouseId(Integer warehouseId) {
            this.warehouseId = warehouseId;
        }

        public Integer getImportId() {
            return importId;
        }

        public void setImportId(Integer importId) {
            this.importId = importId;
        }

        public String getCreationDate() {
            return creationDate;
        }

        public void setCreationDate(String creationDate) {
            this.creationDate = creationDate;
        }

        public List<String> getMatchedQueryNames() {
            return matchedQueryNames;
        }

        public void setMatchedQueryNames(List<String> matchedQueryNames) {
            this.matchedQueryNames = matchedQueryNames;
        }

        public Integer getDistributedId() {
            return distributedId;
        }

        public void setDistributedId(Integer distributedId) {
            this.distributedId = distributedId;
        }

        public String getSId() {
            return sId;
        }

        public void setSId(String sId) {
            this.sId = sId;
        }

        public List<Cell> getCells() {
            return cells;
        }

        public void setCells(List<Cell> cells) {
            this.cells = cells;
        }

        public List<BinaryMetadata> getBinaryMetadata() {
            return binaryMetadata;
        }

        public void setBinaryMetadata(List<BinaryMetadata> binaryMetadata) {
            this.binaryMetadata = binaryMetadata;
        }

        public Number getScore() {
            return score;
        }

        public void setScore(Number score) {
            this.score = score;
        }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Cell {
        @JsonProperty("ColumnId")
        private Integer columnId;

        @JsonProperty("ColumnName")
        private String columnName;

        @JsonProperty("Value")
        private Object value;   // تغییر به Object به خاطر مقادیر مختلف (String, Double و..)

        public Integer getColumnId() {
            return columnId;
        }

        public void setColumnId(Integer columnId) {
            this.columnId = columnId;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public String getColumnName() {
            return columnName;
        }

        public void setColumnName(String columnName) {
            this.columnName = columnName;
        }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class BinaryMetadata {
        @JsonProperty("ColumnId")
        private Integer columnId;

        @JsonProperty("FileId")
        private String fileId;

        @JsonProperty("Name")
        private String name;

        @JsonProperty("Size")
        private Long size;

        @JsonProperty("Extension")
        private String extension;

        public Integer getColumnId() {
            return columnId;
        }

        public void setColumnId(Integer columnId) {
            this.columnId = columnId;
        }

        public String getFileId() {
            return fileId;
        }

        public void setFileId(String fileId) {
            this.fileId = fileId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Long getSize() {
            return size;
        }

        public void setSize(Long size) {
            this.size = size;
        }

        public String getExtension() {
            return extension;
        }

        public void setExtension(String extension) {
            this.extension = extension;
        }
    }
}
