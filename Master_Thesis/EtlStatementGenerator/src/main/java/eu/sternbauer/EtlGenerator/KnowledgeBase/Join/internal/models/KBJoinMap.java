package eu.sternbauer.EtlGenerator.KnowledgeBase.Join.internal.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 * Represents the JoinMap table in the KnowledgeBase and provides mappings for SpringData JDBC.
 * @param joinMapId
 * @param elementName
 * @param condition
 * @param comment
 * @param columns List of all JoinColumns associated with this mapping.
 */
@Table(value = "JoinMap", schema = "kb")
public record KBJoinMap(@Column("join_map_id") @Id Integer joinMapId,
                        @Column("element_name") String elementName,
                        String condition,
                        @Nullable String comment,
                        @MappedCollection(idColumn = "join_map_id", keyColumn = "join_map_id") List<KBJoinColumn> columns) { }
