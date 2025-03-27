package eu.sternbauer.EtlGenerator.KnowledgeBase.Transformation.dto;

/**
 * Data Transfer Object for {@link eu.sternbauer.EtlGenerator.KnowledgeBase.Transformation.internal.models.KBTransformationMap}.<br />
 * Mapping is handled via {@link eu.sternbauer.EtlGenerator.KnowledgeBase.Transformation.internal.mapper.TransformationMapper}.
 * @param columnId
 * @param elementName
 */
public record KBTransformationMapDTO(int columnId,
                                     String elementName) {
}
