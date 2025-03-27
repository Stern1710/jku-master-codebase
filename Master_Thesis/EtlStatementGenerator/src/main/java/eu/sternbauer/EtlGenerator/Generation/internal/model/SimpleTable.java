package eu.sternbauer.EtlGenerator.Generation.internal.model;

/**
 * A minimal subset of all required elements needed from the table elements.
 * As the shortname are only set after the elements are created and Java records to not support mutable fields,
 * this is not a record class.
 */
public class SimpleTable {
    private final int tableId;
    private final String tableName;
    private final String schema;
    private String shortname;
    private final Integer tableSelectId;

    public SimpleTable(int tableId, String tableName, String schema, Integer tableSelectId) {
        this.tableId = tableId;
        this.tableName = tableName;
        this.schema = schema;
        this.tableSelectId = tableSelectId;
        shortname = "";
    }

    public int getTableId() {
        return tableId;
    }

    public String getTableName() {
        return tableName;
    }

    public String getSchema() {
        return schema;
    }

    public Integer getTableSelectId() {
        return tableSelectId;
    }

    public String getBracketShortname() {
        return "[" + getShortname() + "]";
    }

    protected String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }
}
