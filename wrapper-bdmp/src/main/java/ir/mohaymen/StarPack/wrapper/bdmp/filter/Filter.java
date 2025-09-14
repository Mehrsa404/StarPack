package ir.mohaymen.starpack.wrapper.bdmp.filter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Filter {
    @JsonProperty("And")
    private List<FilterItem> and;

    @JsonProperty("Or")
    private List<FilterItem> or;

    @JsonProperty("LeafClause")
    private LeafClause leafClause;

    @JsonProperty("Not")
    private Boolean not;

    @JsonProperty("QueryName")
    private String queryName;

    public List<FilterItem> getAnd() {
        return and;
    }

    public void setAnd(List<FilterItem> and) {
        this.and = and;
    }

    public List<FilterItem> getOr() {
        return or;
    }

    public void setOr(List<FilterItem> or) {
        this.or = or;
    }

    public LeafClause getLeafClause() {
        return leafClause;
    }

    public void setLeafClause(LeafClause leafClause) {
        this.leafClause = leafClause;
    }

    public Boolean getNot() {
        return not;
    }

    public void setNot(Boolean not) {
        this.not = not;
    }

    public String getQueryName() {
        return queryName;
    }

    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }
}
