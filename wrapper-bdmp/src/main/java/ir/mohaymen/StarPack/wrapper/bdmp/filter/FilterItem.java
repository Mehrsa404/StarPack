package ir.mohaymen.starpack.wrapper.bdmp.filter;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FilterItem {
    @JsonProperty("LeafClause")
    private LeafClause leafClause;

    public FilterItem() {
    }

    public FilterItem(LeafClause leafClause) {
        this.leafClause = leafClause;
    }

    public LeafClause getLeafClause() {
        return leafClause;
    }

    public void setLeafClause(LeafClause leafClause) {
        this.leafClause = leafClause;
    }
}
