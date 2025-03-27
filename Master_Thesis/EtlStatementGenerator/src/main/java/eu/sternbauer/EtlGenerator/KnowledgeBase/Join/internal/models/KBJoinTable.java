package eu.sternbauer.EtlGenerator.KnowledgeBase.Join.internal.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 * Represents the JoinTable table in the KnowledgeBase and provides mappings for SpringData JDBC.
 * @param joinTableId
 * @param joinType The type of join used, for example INNER, OUTER, etc.
 * @param table1 One table to be joined
 * @param table2 The other table which is being joined
 * @param condition The join condition
 * @param comment
 * @param mappings List of all mappings associated with the join
 */
@Table(value = "JoinTable", schema = "kb")
public record KBJoinTable(@Column("join_table_id") @Id Integer joinTableId,
                          @Column("join_type") String joinType,
                          @Column("table_1") int table1,
                          @Column("table_2") int table2,
                          String condition,
                          @Nullable String comment,
                          @MappedCollection(keyColumn = "join_table_id", idColumn = "join_table_id") List<KBJoinMap> mappings) { }
