package eu.sternbauer.EtlGenerator.KnowledgeBase.LayoutInfo.dto;

/**
 * Data Transfer Object for {@link eu.sternbauer.EtlGenerator.KnowledgeBase.LayoutInfo.internal.models.KBColumn}.<br />
 * Mapping is handled via {@link eu.sternbauer.EtlGenerator.KnowledgeBase.LayoutInfo.internal.mapper.LayoutInfoMapper}.
 * @param columnId
 * @param columnName
 */
public record KBColumnDTO(int columnId,
                          String columnName) {
}
