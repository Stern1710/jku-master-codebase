package eu.sternbauer.EtlGenerator.KnowledgeBase.Condition.internal.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 * Represents the Condition table in the KnowledgeBase and provides mappings for SpringData JDBC.
 * @param columnConditionId
 * @param columnId
 * @param expression
 * @param comment
 * @param mappings List of all mappings for this condition
 */
@Table(value = "ColumnCondition", schema = "kb")
public record KBCondition(@Column("column_condition_id") @Id int columnConditionId,
                          @Column("column_id") int columnId,
                          String expression,
                          @Nullable String comment,
                          @MappedCollection(idColumn = "column_condition_id", keyColumn = "column_condition_id") List<KBConditionMap> mappings) { }
