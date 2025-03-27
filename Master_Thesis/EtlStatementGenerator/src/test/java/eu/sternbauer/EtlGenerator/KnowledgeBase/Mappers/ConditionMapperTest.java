package eu.sternbauer.EtlGenerator.KnowledgeBase.Mappers;

import eu.sternbauer.EtlGenerator.KnowledgeBase.Condition.dto.KBConditionDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.Condition.dto.KBConditionMapDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.Condition.internal.mapper.ConditionMapper;
import eu.sternbauer.EtlGenerator.KnowledgeBase.Condition.internal.models.KBCondition;
import eu.sternbauer.EtlGenerator.KnowledgeBase.Condition.internal.models.KBConditionMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ConditionMapperTest {
    private static KBCondition refCondition;
    private static KBConditionMap refConditionMap;

    @BeforeAll
    public static void setUp() {
        refConditionMap = new KBConditionMap(10, "name11");
        refCondition = new KBCondition(1, 2, "col3", "comment4", List.of(refConditionMap));
    }

    @Test
    public void testMapperCondition() {
        final KBConditionDTO dto = ConditionMapper.INSTANCE.conditionToDTO(refCondition);
        Assertions.assertEquals(refCondition.columnConditionId(), dto.columnConditionId());
        Assertions.assertEquals(refCondition.columnId(), dto.columnId());
        Assertions.assertEquals(refCondition.expression(), dto.expression());
        Assertions.assertEquals(refCondition.mappings().size(), dto.mappings().size());

        final KBCondition fromDTO = ConditionMapper.INSTANCE.conditionFromDTO(dto);
        Assertions.assertEquals(dto.columnConditionId(), fromDTO.columnConditionId());
        Assertions.assertEquals(dto.columnId(), fromDTO.columnId());
        Assertions.assertEquals(dto.expression(), fromDTO.expression());
        Assertions.assertEquals(dto.mappings().size(), fromDTO.mappings().size());
        Assertions.assertNull(fromDTO.comment());
    }

    @Test
    public void testMapperConditionMap() {
        final KBConditionMapDTO dto = ConditionMapper.INSTANCE.conditionMapToDTO(refConditionMap);
        Assertions.assertEquals(refConditionMap.columnId(), dto.columnId());
        Assertions.assertEquals(refConditionMap.elementName(), dto.elementName());

        final KBConditionMap fromDTO = ConditionMapper.INSTANCE.conditionMapFromDTO(dto);
        Assertions.assertEquals(dto.columnId(), fromDTO.columnId());
        Assertions.assertEquals(dto.elementName(), fromDTO.elementName());
    }

}
