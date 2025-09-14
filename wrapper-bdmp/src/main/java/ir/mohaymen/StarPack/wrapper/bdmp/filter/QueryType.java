package ir.mohaymen.starpack.wrapper.bdmp.filter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum QueryType {
    Equal(0),
    ListEqual(1),
    NotEqual(2),
    IsNull(3),
    IsNotNull(4),
    Range(5),
    LowerThan(6),
    LowerOrEqualThan(7),
    GreaterThan(8),
    GreaterOrEqualThan(9),
    EqualElasticSearch(10),
    StartWith(11),
    EndWith(12),
    Contains(13),
    ContainsFuzzy(14);

    private final int code;
    QueryType(int code) { this.code = code; }

    @JsonValue
    public int getCode() { return code; }

    @JsonCreator
    public static QueryType fromCode(int code) {
        for (QueryType qt : values()) if (qt.code == code) return qt;
        throw new IllegalArgumentException("Unknown QueryType code: " + code);
    }
}
