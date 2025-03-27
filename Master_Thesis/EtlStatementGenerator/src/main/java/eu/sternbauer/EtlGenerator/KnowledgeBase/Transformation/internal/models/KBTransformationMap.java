package eu.sternbauer.EtlGenerator.KnowledgeBase.Transformation.internal.models;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Represents the TransformationMap table in the KnowledgeBase and provides mappings for SpringData JDBC.
 * @param columnId
 * @param elementName
 */
@Table(value = "TransformationFromMap", schema = "kb")
public record KBTransformationMap(@Column("column_id") int columnId,
                                  @Column("element_name") String elementName) {
}
