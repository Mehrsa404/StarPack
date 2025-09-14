package ir.mohaymen.starpack.wrapper.bdmp.filter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ValueClause {
    @JsonProperty("ClauseValueType")
    private Integer clauseValueType;

    @JsonProperty("Value")
    private String value;

    public ValueClause() {}

    public ValueClause(Integer clauseValueType, String value) {
        this.clauseValueType = clauseValueType;
        this.value = value;
    }

    public Integer getClauseValueType() { return clauseValueType; }
    public void setClauseValueType(Integer clauseValueType) { this.clauseValueType = clauseValueType; }
    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
}
