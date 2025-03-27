package eu.sternbauer.EtlGenerator.KnowledgeBase.Transformation.internal.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 * Represents the TransformationMap table in the KnowledgeBase and provides mappings for SpringData JDBC.
 * @param transformationOpId
 * @param columnId
 * @param sourceDatabase
 * @param expression
 * @param comment
 * @param mappings List of all transformation mappings for this operation
 */
@Table(value = "TransformationOperation", schema = "kb")
public record KBTransformationOp(@Column("transformation_op_id") @Id int transformationOpId,
                                 @Column("column_id") int columnId,
                                 @Column("source_database") int sourceDatabase,
                                 String expression,
                                 @Nullable String comment,
                                 @MappedCollection(keyColumn = "transformation_op_id", idColumn = "transformation_op_id") List<KBTransformationMap> mappings) { }
