package eu.sternbauer.EtlGenerator.KnowledgeBase.LayoutInfo.internal.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 * Represents the Database table in the KnowledgeBase and provides mappings for SpringData JDBC.
 * @param tableId ID of the table inside the knowledge base
 * @param tableName Name of the table
 * @param schema Table schema, example "dbo"
 * @param tableSelectId Links to the custom select statement, if it exists
 * @param columns List of all tables
 */
@Table(value = "TableInformation", schema = "kb")
public record KBTable(
        @Id @Column("table_id") int tableId,
        @Column("table_name") String tableName,
        String schema,
        @Column("table_select_id") @Nullable Integer tableSelectId,
        @MappedCollection(idColumn = "table_id", keyColumn = "table_id") List<KBColumn> columns
) { }
