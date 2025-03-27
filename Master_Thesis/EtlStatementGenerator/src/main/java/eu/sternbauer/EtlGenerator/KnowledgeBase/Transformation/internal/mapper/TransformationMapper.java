package eu.sternbauer.EtlGenerator.KnowledgeBase.Transformation.internal.mapper;

import eu.sternbauer.EtlGenerator.KnowledgeBase.Transformation.dto.KBTransformationMapDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.Transformation.dto.KBTransformationOpDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.Transformation.internal.models.KBTransformationMap;
import eu.sternbauer.EtlGenerator.KnowledgeBase.Transformation.internal.models.KBTransformationOp;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Maps Transformation data to and from data transfer objects.
 * The MapStruct library will auto-generated the actual code during build time.
 */
@Mapper
public interface TransformationMapper {
    TransformationMapper INSTANCE = Mappers.getMapper(TransformationMapper.class);

    KBTransformationOpDTO transformationOpToDTO(KBTransformationOp op);
    KBTransformationOp transformationOpFromDTO(KBTransformationOpDTO dto);

    KBTransformationMapDTO transformationMapToDTO(KBTransformationMap op);
    KBTransformationMap transformationMapFromDTO(KBTransformationMapDTO dto);
}
