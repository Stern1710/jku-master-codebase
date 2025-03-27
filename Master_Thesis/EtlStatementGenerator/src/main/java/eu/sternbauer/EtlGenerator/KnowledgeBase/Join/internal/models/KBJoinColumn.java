package eu.sternbauer.EtlGenerator.KnowledgeBase.Join.internal.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Represents the JoinColumn table in the KnowledgeBase and provides mappings for SpringData JDBC.
 * @param joinColumnId
 * @param columnId Reference to the column table
 * @param elementName name of this join column element inside the mapping string
 */
@Table(value = "JoinColumn", schema = "kb")
public record KBJoinColumn(@Column("join_column_id") @Id Integer joinColumnId,
                           @Column("column_id") int columnId,
                           @Column("element_name") String elementName) {
}
