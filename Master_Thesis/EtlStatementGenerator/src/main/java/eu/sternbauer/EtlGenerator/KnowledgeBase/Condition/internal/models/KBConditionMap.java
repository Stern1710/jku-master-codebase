package eu.sternbauer.EtlGenerator.KnowledgeBase.Condition.internal.models;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Represents the ConditionMap table in the KnowledgeBase and provides mappings for SpringData JDBC.
 * @param columnId Reference to the column which should be substituted in the mapping
 * @param elementName name of this element in the mapping
 */
@Table(value = "ColumnConditionMap", schema = "kb")
public record KBConditionMap(@Column("column_id") int columnId,
                             @Column("element_name") String elementName) {
}
