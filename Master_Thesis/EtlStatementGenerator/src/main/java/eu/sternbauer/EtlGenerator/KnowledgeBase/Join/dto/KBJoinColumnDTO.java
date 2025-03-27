package eu.sternbauer.EtlGenerator.KnowledgeBase.Join.dto;

/**
 * Data Transfer Object for {@link eu.sternbauer.EtlGenerator.KnowledgeBase.Join.internal.models.KBJoinColumn}.<br />
 * Mapping is handled via {@link eu.sternbauer.EtlGenerator.KnowledgeBase.Join.internal.mapper.JoinMapper}.
 * @param joinColumnId
 * @param columnId
 * @param elementName
 */
public record KBJoinColumnDTO(Integer joinColumnId,
                              int columnId,
                              String elementName) { }
