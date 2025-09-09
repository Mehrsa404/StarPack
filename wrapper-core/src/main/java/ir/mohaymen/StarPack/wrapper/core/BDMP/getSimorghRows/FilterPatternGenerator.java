package ir.mohaymen.StarPack.wrapper.core.BDMP.getSimorghRows;

import java.util.*;

public final class FilterPatternGenerator {

    private FilterPatternGenerator() {}

    /* ===================== Enums (طبق داک) ===================== */
    public static final class QueryType {
        public static final int EQ = 0;
        public static final int IN = 1;           // == (List)
        public static final int NE = 2;
        public static final int IS_NULL = 3;
        public static final int NOT_NULL = 4;
        public static final int RANGE = 5;
        public static final int LT = 6;
        public static final int LTE = 7;
        public static final int GT = 8;
        public static final int GTE = 9;
        public static final int EQ_SID = 10;      // ==(SI)
        public static final int STARTS_WITH = 11;
        public static final int ENDS_WITH = 12;
        public static final int CONTAINS = 13;
        public static final int CONTAINS_FUZZY = 14;
        private QueryType() {}
    }

    public static final class ClauseValueType {
        public static final int VALUE = 0;
        public static final int RANGE_VALUE = 1;
        public static final int LIST_VALUE = 2;
        private ClauseValueType() {}
    }

    /* ===================== مدل داخلی DSL ===================== */

    /** یک نود Clause (یا برگ است یا ترکیبی از AND/OR با NOT اختیاری) */
    public static final class ClauseNode {
        private Map<String,Object> leafClause;     // {"ColumnId", "...", "QueryType", ..., "Value":{...}}
        private List<ClauseNode> andClauses;
        private List<ClauseNode> orClauses;
        private boolean not;
        private String queryName;                 // اختیاری

        private ClauseNode() {}

        public ClauseNode not() { this.not = !this.not; return this; }

        public ClauseNode queryName(String qn) { this.queryName = qn; return this; }

        /** خروجی نهایی مطابق داک (Map<String,Object>) */
        public Map<String,Object> toMap() {
            var out = new LinkedHashMap<String,Object>();
            if (leafClause != null) out.put("LeafClause", leafClause);
            if (andClauses != null && !andClauses.isEmpty()) {
                out.put("And", toList(andClauses));
            }
            if (orClauses != null && !orClauses.isEmpty()) {
                out.put("Or", toList(orClauses));
            }
            if (queryName != null) out.put("QueryName", queryName);
            if (not) out.put("Not", true);
            return out;
        }

        private static List<Map<String,Object>> toList(List<ClauseNode> nodes) {
            var lst = new ArrayList<Map<String,Object>>(nodes.size());
            for (ClauseNode n : nodes) lst.add(n.toMap());
            return lst;
        }
    }

    /* ===================== سازنده‌های برگ (LeafClause) ===================== */

    private static ClauseNode leaf(int columnId, int queryType, Map<String,Object> clauseValue) {
        var leaf = new LinkedHashMap<String,Object>();
        leaf.put("ColumnId", columnId);
        leaf.put("QueryType", queryType);
        if (clauseValue != null) leaf.put("Value", clauseValue);

        var node = new ClauseNode();
        node.leafClause = leaf;
        return node;
    }

    private static Map<String,Object> value(Object v) {
        var m = new LinkedHashMap<String,Object>();
        m.put("ClauseValueType", ClauseValueType.VALUE);
        m.put("Value", Objects.toString(v, null));  // داک گفته String representation
        return m;
    }

    private static Map<String,Object> range(Object lowerInclusive, Object upperExclusive) {
        var inner = new LinkedHashMap<String,Object>();
        inner.put("LowerBoundIncluding", Objects.toString(lowerInclusive, null));
        inner.put("UpperBoundExcluding", Objects.toString(upperExclusive, null));
        var m = new LinkedHashMap<String,Object>();
        m.put("ClauseValueType", ClauseValueType.RANGE_VALUE);
        m.put("RangeValue", inner);
        return m;
    }

    private static Map<String,Object> listValues(Collection<?> values) {
        var inner = new LinkedHashMap<String,Object>();
        var vals = new ArrayList<String>();
        for (Object v : values) vals.add(Objects.toString(v, null));
        inner.put("Values", vals);

        var m = new LinkedHashMap<String,Object>();
        m.put("ClauseValueType", ClauseValueType.LIST_VALUE);
        m.put("ListValue", inner);
        return m;
    }

    /* ===================== شرط‌های آماده ===================== */

    public static ClauseNode eq(int col, Object v)       { return leaf(col, QueryType.EQ, value(v)); }
    public static ClauseNode ne(int col, Object v)       { return leaf(col, QueryType.NE, value(v)); }
    public static ClauseNode gt(int col, Object v)       { return leaf(col, QueryType.GT, value(v)); }
    public static ClauseNode gte(int col, Object v)      { return leaf(col, QueryType.GTE, value(v)); }
    public static ClauseNode lt(int col, Object v)       { return leaf(col, QueryType.LT, value(v)); }
    public static ClauseNode lte(int col, Object v)      { return leaf(col, QueryType.LTE, value(v)); }
    public static ClauseNode startsWith(int col, String s) { return leaf(col, QueryType.STARTS_WITH, value(s)); }
    public static ClauseNode endsWith(int col, String s)   { return leaf(col, QueryType.ENDS_WITH, value(s)); }
    public static ClauseNode contains(int col, String s)   { return leaf(col, QueryType.CONTAINS, value(s)); }
    public static ClauseNode containsFuzzy(int col, String s){ return leaf(col, QueryType.CONTAINS_FUZZY, value(s)); }
    public static ClauseNode inList(int col, Collection<?> vals){ return leaf(col, QueryType.IN, listValues(vals)); }
    public static ClauseNode rangeInclusiveExclusive(int col, Object lowerInc, Object upperExc) {
        return leaf(col, QueryType.RANGE, range(lowerInc, upperExc));
    }
    public static ClauseNode isNull(int col)             { return leaf(col, QueryType.IS_NULL, null); }
    public static ClauseNode notNull(int col)            { return leaf(col, QueryType.NOT_NULL, null); }
    public static ClauseNode eqSid(int col, Object sid)  { return leaf(col, QueryType.EQ_SID, value(sid)); }

    /* ===================== ترکیب‌کننده‌ها (AND/OR/NOT) ===================== */

    public static ClauseNode and(ClauseNode... nodes) {
        var n = new ClauseNode();
        n.andClauses = Arrays.asList(nodes);
        return n;
    }

    public static ClauseNode or(ClauseNode... nodes) {
        var n = new ClauseNode();
        n.orClauses = Arrays.asList(nodes);
        return n;
    }

    public static ClauseNode not(ClauseNode node) {
        node.not();
        return node;
    }
}
