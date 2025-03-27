package eu.sternbauer.EtlGenerator.KnowledgeBase.Condition.internal.mapper;

import eu.sternbauer.EtlGenerator.KnowledgeBase.Condition.dto.KBConditionDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.Condition.dto.KBConditionMapDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.Condition.internal.models.KBCondition;
import eu.sternbauer.EtlGenerator.KnowledgeBase.Condition.internal.models.KBConditionMap;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Maps ConditionMapper data to and from data transfer objects.
 * The MapStruct library will auto-generated the actual code during build time.
 */
@Mapper
public interface ConditionMapper {
    ConditionMapper INSTANCE = Mappers.getMapper(ConditionMapper.class);

    //Mapping conditions
    KBConditionDTO conditionToDTO(KBCondition condition);
    KBCondition conditionFromDTO(KBConditionDTO dto);

    //Mapping condition maps
    KBConditionMapDTO conditionMapToDTO(KBConditionMap conditionMap);
    KBConditionMap conditionMapFromDTO(KBConditionMapDTO dto);
}
