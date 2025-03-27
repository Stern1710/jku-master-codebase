package eu.sternbauer.EtlGenerator.KnowledgeBase.TableSelect.internal.models;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Represents the SelectMap table in the KnowledgeBase and provides mappings for SpringData JDBC.
 * @param columnId
 * @param elementName
 */
@Table(value = "TableSelectMap", schema = "kb")
public record KBSelectMap (@Column("column_id") int columnId,
                           @Column("element_name") String elementName) {
}
