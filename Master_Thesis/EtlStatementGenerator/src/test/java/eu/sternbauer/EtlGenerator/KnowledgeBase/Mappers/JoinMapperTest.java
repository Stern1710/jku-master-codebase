package eu.sternbauer.EtlGenerator.KnowledgeBase.Mappers;

import eu.sternbauer.EtlGenerator.KnowledgeBase.Join.dto.KBJoinColumnDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.Join.dto.KBJoinMapDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.Join.dto.KBJoinTableDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.Join.internal.mapper.JoinMapper;
import eu.sternbauer.EtlGenerator.KnowledgeBase.Join.internal.models.KBJoinColumn;
import eu.sternbauer.EtlGenerator.KnowledgeBase.Join.internal.models.KBJoinMap;
import eu.sternbauer.EtlGenerator.KnowledgeBase.Join.internal.models.KBJoinTable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class JoinMapperTest {
    private static KBJoinTable refJoinTable;
    private static KBJoinMap refJoinMap;
    private static KBJoinColumn refJoinColumn;


    @BeforeAll
    public static void setUp() {
        refJoinColumn = new KBJoinColumn(20, 21, "name22");
        refJoinMap = new KBJoinMap(10, "name11", "cond12", "comment13", List.of(refJoinColumn));
        refJoinTable = new KBJoinTable(1, "INNER", 3, 4, "cond5", "comment6", List.of(refJoinMap));
    }

    @Test
    public void testMapperJoinTable() {
        final KBJoinTableDTO dto = JoinMapper.INSTANCE.joinTableToDTO(refJoinTable);
        Assertions.assertEquals(refJoinTable.joinTableId(), dto.joinTableId());
        Assertions.assertEquals(refJoinTable.joinType(), dto.joinType());
        Assertions.assertEquals(refJoinTable.table1(), dto.table1());
        Assertions.assertEquals(refJoinTable.table2(), dto.table2());
        Assertions.assertEquals(refJoinTable.condition(), dto.condition());
        Assertions.assertEquals(refJoinTable.mappings().size(), dto.mappings().size());

        final KBJoinTable fromDTO = JoinMapper.INSTANCE.joinTableFromDTO(dto);
        Assertions.assertEquals(dto.joinTableId(), fromDTO.joinTableId());
        Assertions.assertEquals(dto.joinType(), fromDTO.joinType());
        Assertions.assertEquals(dto.table1(), fromDTO.table1());
        Assertions.assertEquals(dto.table2(), fromDTO.table2());
        Assertions.assertEquals(dto.condition(), fromDTO.condition());
        Assertions.assertEquals(dto.mappings().size(), fromDTO.mappings().size());
        Assertions.assertNull(fromDTO.comment());
    }

    @Test
    public void testMapperJoinMap() {
        final KBJoinMapDTO dto = JoinMapper.INSTANCE.joinMapToDTO(refJoinMap);
        Assertions.assertEquals(refJoinMap.joinMapId(), dto.joinMapId());
        Assertions.assertEquals(refJoinMap.elementName(), dto.elementName());
        Assertions.assertEquals(refJoinMap.condition(), dto.condition());
        Assertions.assertEquals(refJoinMap.columns().size(), dto.columns().size());

        final KBJoinMap fromDTO = JoinMapper.INSTANCE.joinMapFromDTO(dto);
        Assertions.assertEquals(dto.joinMapId(), fromDTO.joinMapId());
        Assertions.assertEquals(dto.elementName(), fromDTO.elementName());
        Assertions.assertEquals(dto.condition(), fromDTO.condition());
        Assertions.assertEquals(dto.columns().size(), fromDTO.columns().size());
        Assertions.assertNull(fromDTO.comment());
    }

    @Test
    public void testMapperJoinColumn() {
        final KBJoinColumnDTO dto = JoinMapper.INSTANCE.joinColumnToDTO(refJoinColumn);
        Assertions.assertEquals(refJoinColumn.joinColumnId(), dto.joinColumnId());
        Assertions.assertEquals(refJoinColumn.columnId(), dto.columnId());
        Assertions.assertEquals(refJoinColumn.elementName(), dto.elementName());

        final KBJoinColumn fromDTO = JoinMapper.INSTANCE.joinColumnFromDTO(dto);
        Assertions.assertEquals(dto.joinColumnId(), fromDTO.joinColumnId());
        Assertions.assertEquals(dto.columnId(), fromDTO.columnId());
        Assertions.assertEquals(dto.elementName(), fromDTO.elementName());
    }
}
