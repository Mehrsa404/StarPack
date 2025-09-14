package ir.mohaymen.starpack.wrapper.bdmp.filter;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LeafClause {
    @JsonProperty("ColumnId")
    private int columnId;

    @JsonProperty("QueryType")
    private QueryType queryType;

    @JsonProperty("Value")
    private ValueClause value;

    public LeafClause() {
    }

    public LeafClause(int columnId, QueryType queryType, ValueClause value) {
        this.columnId = columnId;
        this.queryType = queryType;
        this.value = value;
    }

    public int getColumnId() {
        return columnId;
    }

    public void setColumnId(int columnId) {
        this.columnId = columnId;
    }

    public QueryType getQueryType() {
        return queryType;
    }

    public void setQueryType(QueryType queryType) {
        this.queryType = queryType;
    }

    public ValueClause getValue() {
        return value;
    }

    public void setValue(ValueClause value) {
        this.value = value;
    }
}
