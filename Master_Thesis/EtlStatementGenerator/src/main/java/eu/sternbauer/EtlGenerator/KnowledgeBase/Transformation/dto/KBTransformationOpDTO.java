package eu.sternbauer.EtlGenerator.KnowledgeBase.Transformation.dto;

import java.util.List;

/**
 * Data Transfer Object for {@link eu.sternbauer.EtlGenerator.KnowledgeBase.Transformation.internal.models.KBTransformationOp}.<br />
 * Mapping is handled via {@link eu.sternbauer.EtlGenerator.KnowledgeBase.Transformation.internal.mapper.TransformationMapper}.
 * @param transformationOpId
 * @param columnId
 * @param sourceDatabase
 * @param expression
 * @param mappings
 */
public record KBTransformationOpDTO(int transformationOpId,
                                    int columnId,
                                    int sourceDatabase,
                                    String expression,
                                    List<KBTransformationMapDTO> mappings) { }
