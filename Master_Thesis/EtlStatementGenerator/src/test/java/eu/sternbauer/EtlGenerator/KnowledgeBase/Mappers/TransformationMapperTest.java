package eu.sternbauer.EtlGenerator.KnowledgeBase.Mappers;

import eu.sternbauer.EtlGenerator.KnowledgeBase.Transformation.dto.KBTransformationMapDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.Transformation.dto.KBTransformationOpDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.Transformation.internal.mapper.TransformationMapper;
import eu.sternbauer.EtlGenerator.KnowledgeBase.Transformation.internal.models.KBTransformationMap;
import eu.sternbauer.EtlGenerator.KnowledgeBase.Transformation.internal.models.KBTransformationOp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TransformationMapperTest {
    private static KBTransformationOp refTransformationOp;
    private static KBTransformationMap refTransformationMap;

    @BeforeAll
    public static void setUp() {
        refTransformationMap = new KBTransformationMap(10, "name11");
        refTransformationOp = new KBTransformationOp(1, 2, 3,"expression3", "comment4", List.of(refTransformationMap));
    }

    @Test
    public void testMapperTransformationOp() {
        final KBTransformationOpDTO dto = TransformationMapper.INSTANCE.transformationOpToDTO(refTransformationOp);
        Assertions.assertEquals(refTransformationOp.transformationOpId(), dto.transformationOpId());
        Assertions.assertEquals(refTransformationOp.columnId(), dto.columnId());
        Assertions.assertEquals(refTransformationOp.sourceDatabase(), dto.sourceDatabase());
        Assertions.assertEquals(refTransformationOp.expression(), dto.expression());
        Assertions.assertEquals(refTransformationOp.mappings().size(), dto.mappings().size());

        final KBTransformationOp fromDTO = TransformationMapper.INSTANCE.transformationOpFromDTO(dto);
        Assertions.assertEquals(dto.transformationOpId(), fromDTO.transformationOpId());
        Assertions.assertEquals(dto.columnId(), fromDTO.columnId());
        Assertions.assertEquals(dto.sourceDatabase(), fromDTO.sourceDatabase());
        Assertions.assertEquals(dto.expression(), fromDTO.expression());
        Assertions.assertEquals(dto.mappings().size(), fromDTO.mappings().size());
        Assertions.assertNull(fromDTO.comment());
    }

    @Test
    public void testMapperTransformationMap() {
        final KBTransformationMapDTO dto = TransformationMapper.INSTANCE.transformationMapToDTO(refTransformationMap);
        Assertions.assertEquals(refTransformationMap.columnId(), dto.columnId());
        Assertions.assertEquals(refTransformationMap.elementName(), dto.elementName());

        final KBTransformationMap fromDTO = TransformationMapper.INSTANCE.transformationMapFromDTO(dto);
        Assertions.assertEquals(dto.columnId(), fromDTO.columnId());
        Assertions.assertEquals(dto.elementName(), fromDTO.elementName());
    }
}
