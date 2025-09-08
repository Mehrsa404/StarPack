package ir.mohaymen.StarPack.wrapper.core.domain.BDMP;

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
        return "GetSimorghRowsOutputDTO{" +
               "dataList=" + dataList +
               ", foundItemsCount=" + foundItemsCount +
               ", took=" + took +
               ", page=" + page +
               ", warningMessage='" + warningMessage + '\'' +
               '}';
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class RowItem {
        @JsonProperty("WarehouseId")
        public Integer warehouseId;

        @JsonProperty("ImportId")
        public Integer importId;

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
            return "RowItem{" +
                   "warehouseId=" + warehouseId +
                   ", importId=" + importId +
                   ", creationDate='" + creationDate + '\'' +
                   ", matchedQueryNames=" + matchedQueryNames +
                   ", distributedId=" + distributedId +
                   ", sId='" + sId + '\'' +
                   ", cells=" + cells +
                   ", binaryMetadata=" + binaryMetadata +
                   ", score=" + score +
                   '}';
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

        @Override
        public String toString() {
            return "Cell{" +
                   "type='" + type + '\'' +
                   ", name='" + name + '\'' +
                   ", extension='" + extension + '\'' +
                   ", columnId=" + columnId +
                   ", value=" + value +
                   '}';
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

        @JsonProperty("Extension")
        public String extension;

        @JsonProperty("Size")
        public Long size;

        @Override
        public String toString() {
            return "BinaryMetadata{" +
                   "columnId=" + columnId +
                   ", fileId='" + fileId + '\'' +
                   ", name='" + name + '\'' +
                   ", extension='" + extension + '\'' +
                   ", size=" + size +
                   '}';
        }
    }
}
