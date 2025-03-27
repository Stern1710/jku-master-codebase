package eu.sternbauer.EtlGenerator.KnowledgeBase.LayoutInfo.internal.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Represents the Column table in the KnowledgeBase and provides mappings for SpringData JDBC.
 * @param columnId
 * @param columnName Name of the column in either source or target
 */
@Table(value = "ColumnInformation", schema = "kb")
public record KBColumn(@Id @Column("column_id") int columnId,
                       @Column("column_name") String columnName) { }
