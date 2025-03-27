package eu.sternbauer.EtlGenerator.Generation.internal.model;

/**
 * Representation of the combination Database -> Table -> Column in one class
 * @param database
 * @param table
 * @param column
 */
public record FullLayoutElement(SimpleDb database, SimpleTable table, SimpleColumn column) {

    /**
     * Gives the database name with an added {@code -Server} at the end
     * @return String
     */
    public String linkedServerName() {
        return database.dbName() + "-Server";
    }

    /**
     * Gives the shortname path with the column name. If the shortcode is {@code at} and the column name {@code sternbauer},
     * the shortname path will be {@code [at].[sternbauer]}.
     * @return String containing the shortname and column name, all in brackets
     */
    public String getShortnamePath() {
        return "[" + table.getShortname() + "]." +
                "[" + column.columnName() + "]";
    }

    /**
     * Gives the full path, inlcuding the linked server, database, table and column
     * @return String containing all elements in individual brackets.
     */
    public String getFullTablePath() {
        return "[" + linkedServerName() + "]." +
                "[" + database.dbName() + "]." +
                "[" + table.getSchema() + "]." +
                "[" + table.getTableName() + "]";
    }
}
