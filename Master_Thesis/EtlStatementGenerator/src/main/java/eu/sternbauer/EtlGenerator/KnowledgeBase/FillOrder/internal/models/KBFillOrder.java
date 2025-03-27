package eu.sternbauer.EtlGenerator.KnowledgeBase.FillOrder.internal.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Represents the FillOrder table in the KnowledgeBase and provides mappings for SpringData JDBC.
 * @param fillOrderId
 * @param tableName Name of the table
 * @param tableSchema Schema of the table, example "dbo"
 * @param orderPriority Lower indicates higher priority
 */
@Table(value = "FillOrder", schema = "kb")
public record KBFillOrder(@Column("fill_order_id") @Id int fillOrderId,
                          @Column("table_name") String tableName,
                          @Column("table_schema") String tableSchema,
                          @Column("order_priority") int orderPriority) {
}
