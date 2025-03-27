package eu.sternbauer.EtlGenerator.KnowledgeBase.Join.internal.mapper;

import eu.sternbauer.EtlGenerator.KnowledgeBase.Join.dto.KBJoinColumnDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.Join.dto.KBJoinMapDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.Join.dto.KBJoinTableDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.Join.internal.models.KBJoinColumn;
import eu.sternbauer.EtlGenerator.KnowledgeBase.Join.internal.models.KBJoinMap;
import eu.sternbauer.EtlGenerator.KnowledgeBase.Join.internal.models.KBJoinTable;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Maps JoinMapper data to and from data transfer objects.
 * The MapStruct library will auto-generated the actual code during build time.
 */
@Mapper
public interface JoinMapper {
    JoinMapper INSTANCE = Mappers.getMapper(JoinMapper.class);

    KBJoinTableDTO joinTableToDTO(KBJoinTable joinTable);
    KBJoinTable joinTableFromDTO(KBJoinTableDTO joinTableDTO);

    KBJoinMapDTO joinMapToDTO(KBJoinMap joinMap);
    KBJoinMap joinMapFromDTO(KBJoinMapDTO joinMapDTO);

    KBJoinColumnDTO joinColumnToDTO(KBJoinColumn joinColumn);
    KBJoinColumn joinColumnFromDTO(KBJoinColumnDTO joinColumnDTO);
}
