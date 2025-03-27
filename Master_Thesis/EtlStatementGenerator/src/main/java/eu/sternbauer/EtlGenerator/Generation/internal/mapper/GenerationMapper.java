package eu.sternbauer.EtlGenerator.Generation.internal.mapper;

import eu.sternbauer.EtlGenerator.Generation.internal.model.SimpleColumn;
import eu.sternbauer.EtlGenerator.Generation.internal.model.SimpleDb;
import eu.sternbauer.EtlGenerator.Generation.internal.model.SimpleTable;
import eu.sternbauer.EtlGenerator.KnowledgeBase.LayoutInfo.dto.KBColumnDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.LayoutInfo.dto.KBDatabaseDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.LayoutInfo.dto.KBTableDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Maps LayoutInfo DTOs to and from the data objects use in generation.
 * The MapStruct library will auto-generated the actual code during build time.
 */
@Mapper
public interface GenerationMapper {
    GenerationMapper INSTANCE = Mappers.getMapper(GenerationMapper.class);

    SimpleDb simpleDbFromDTO(KBDatabaseDTO dbDTO);
    SimpleTable simpleTableFromDTO(KBTableDTO tableDTO);
    SimpleColumn simpleColumnFromDTO(KBColumnDTO columnDTO);
}
