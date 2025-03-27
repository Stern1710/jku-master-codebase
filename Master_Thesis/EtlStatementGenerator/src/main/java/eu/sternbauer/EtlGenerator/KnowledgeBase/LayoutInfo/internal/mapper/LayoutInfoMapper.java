package eu.sternbauer.EtlGenerator.KnowledgeBase.LayoutInfo.internal.mapper;

import eu.sternbauer.EtlGenerator.KnowledgeBase.LayoutInfo.dto.KBColumnDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.LayoutInfo.dto.KBDatabaseDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.LayoutInfo.dto.KBTableDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.LayoutInfo.internal.models.KBColumn;
import eu.sternbauer.EtlGenerator.KnowledgeBase.LayoutInfo.internal.models.KBDatabase;
import eu.sternbauer.EtlGenerator.KnowledgeBase.LayoutInfo.internal.models.KBTable;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Maps LayoutInfo data to and from data transfer objects.
 * The MapStruct library will auto-generated the actual code during build time.
 */
@Mapper
public interface LayoutInfoMapper {
    LayoutInfoMapper INSTANCE = Mappers.getMapper(LayoutInfoMapper.class);

    KBDatabaseDTO databaseToDTO(KBDatabase database);
    KBDatabase databaseFromDTO(KBDatabaseDTO databaseDTO);

    KBTableDTO tableToDTO(KBTable table);
    KBTable tableFromDTO(KBTableDTO tableDTO);

    KBColumnDTO columnToDTO(KBColumn column);
    KBColumn columnFromDTO(KBColumnDTO columnDTO);
}
