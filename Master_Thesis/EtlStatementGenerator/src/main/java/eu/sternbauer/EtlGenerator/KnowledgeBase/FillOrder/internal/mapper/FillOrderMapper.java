package eu.sternbauer.EtlGenerator.KnowledgeBase.FillOrder.internal.mapper;

import eu.sternbauer.EtlGenerator.KnowledgeBase.FillOrder.dto.KBFillOrderDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.FillOrder.internal.models.KBFillOrder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Maps FillOrder data to and from data transfer objects.
 * The MapStruct library will auto-generated the actual code during build time.
 */
@Mapper
public interface FillOrderMapper {
    FillOrderMapper INSTANCE = Mappers.getMapper(FillOrderMapper.class);

    KBFillOrderDTO fillOrderToDTO(KBFillOrder fillOrder);
    KBFillOrder fillOrderFromDTO(KBFillOrderDTO fillOrderDTO);
}
