package eu.sternbauer.EtlGenerator.KnowledgeBase.Join.dto;

import java.util.List;

/**
 * * Data Transfer Object for {@link eu.sternbauer.EtlGenerator.KnowledgeBase.Join.internal.models.KBJoinTable}.<br />
 * Mapping is handled via {@link eu.sternbauer.EtlGenerator.KnowledgeBase.Join.internal.mapper.JoinMapper}.
 * @param joinTableId
 * @param joinType
 * @param table1
 * @param table2
 * @param condition
 * @param mappings
 */
public record KBJoinTableDTO(Integer joinTableId,
                             String joinType,
                             int table1,
                             int table2,
                             String condition,
                             List<KBJoinMapDTO> mappings) { }
