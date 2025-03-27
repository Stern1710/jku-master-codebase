package eu.sternbauer.EtlGenerator.KnowledgeBase.Join.dto;

import java.util.List;

/**
 * Data Transfer Object for {@link eu.sternbauer.EtlGenerator.KnowledgeBase.Join.internal.models.KBJoinMap}.<br />
 * Mapping is handled via {@link eu.sternbauer.EtlGenerator.KnowledgeBase.Join.internal.mapper.JoinMapper}.
 * @param joinMapId
 * @param elementName
 * @param condition
 * @param columns
 */
public record KBJoinMapDTO(Integer joinMapId,
                           String elementName,
                           String condition,
                           List<KBJoinColumnDTO> columns
){ }
