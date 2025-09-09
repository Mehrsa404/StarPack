package ir.mohaymen.StarPack.wrapper.core.BDMP.getSimorghRows;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetSimorghRowsOutputDTO {

    @JsonProperty("DataList")
    public List<RowItem> dataList;

    @JsonProperty("FoundItemsCount")
    public Long foundItemsCount;

    @JsonProperty("Took")
    public Long took;

    @JsonProperty("Page")
    public Object page;

    @JsonProperty("WarningMessage")
    public String warningMessage;

    @Override
    public String toString() {
        return "GetSimorghRowsOutputDTO{" + "dataList=" + dataList + ", foundItemsCount=" + foundItemsCount + ", took=" + took + ", page=" + page +
               ", warningMessage='" + warningMessage + '\'' + '}';
    }

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

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class RowItem {
        @JsonProperty("WarehouseId")
        public Integer warehouseId;

        @JsonProperty("ImportId")
        public Integer importId;

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

        public void setsId(String sId) {
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

        @JsonProperty("CreationDate")
        public String creationDate;

        @JsonProperty("MatchedQueryNames")
        public List<String> matchedQueryNames;

        @JsonProperty("DistributedId")
        public Integer distributedId;

        @JsonProperty("SId")
        public String sId;

        @JsonProperty("Cells")
        public List<Cell> cells;

        @JsonProperty("BinaryMetadata")
        public List<BinaryMetadata> binaryMetadata;

        @JsonProperty("Score")
        public Number score;

        @Override
        public String toString() {
            return "RowItem{" + "warehouseId=" + warehouseId + ", importId=" + importId + ", creationDate='" + creationDate + '\'' +
                   ", matchedQueryNames=" + matchedQueryNames + ", distributedId=" + distributedId + ", sId='" + sId + '\'' + ", cells=" + cells +
                   ", binaryMetadata=" + binaryMetadata + ", score=" + score + '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Cell {
        @JsonProperty("$type")
        public String type;


        @JsonProperty("Name")
        public String name;

        @JsonProperty("Extension")
        public String extension;

        @JsonProperty("ColumnId")
        public Integer columnId;

        @JsonProperty("Value")
        public Object value;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getExtension() {
            return extension;
        }

        public void setExtension(String extension) {
            this.extension = extension;
        }

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
        @Override
        public String toString() {
            return "Cell{" + "type='" + type + '\'' + ", name='" + name + '\'' + ", extension='" + extension + '\'' + ", columnId=" + columnId +
                   ", value=" + value + '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class BinaryMetadata {
        @JsonProperty("ColumnId")
        public Integer columnId;

        @JsonProperty("FileId")
        public String fileId;

        @JsonProperty("Name")
        public String name;

        @JsonProperty("Size")
        public Long size;

        @JsonProperty("Extension")
        public String extension;

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFileId() {
            return fileId;
        }

        public void setFileId(String fileId) {
            this.fileId = fileId;
        }

        public Integer getColumnId() {
            return columnId;
        }

        public void setColumnId(Integer columnId) {
            this.columnId = columnId;
        }

        @Override
        public String toString() {
            return "BinaryMetadata{" + "columnId=" + columnId + ", fileId='" + fileId + '\'' + ", name='" + name + '\'' + ", extension='" +
                   extension + '\'' + ", size=" + size + '}';
        }
    }
}
