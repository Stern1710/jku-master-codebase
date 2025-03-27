package eu.sternbauer.EtlGenerator.KnowledgeBase.Mappers;

import eu.sternbauer.EtlGenerator.KnowledgeBase.LayoutInfo.dto.KBColumnDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.LayoutInfo.dto.KBDatabaseDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.LayoutInfo.dto.KBTableDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.LayoutInfo.internal.mapper.LayoutInfoMapper;
import eu.sternbauer.EtlGenerator.KnowledgeBase.LayoutInfo.internal.models.KBColumn;
import eu.sternbauer.EtlGenerator.KnowledgeBase.LayoutInfo.internal.models.KBDatabase;
import eu.sternbauer.EtlGenerator.KnowledgeBase.LayoutInfo.internal.models.KBTable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LayoutInfoMapperTest {

    private static KBDatabase refDatabase;
    private static KBTable refTable;
    private static KBColumn refColumn;

    @BeforeAll
    public static void setUp() {
        refColumn = new KBColumn(20, "name21");
        refTable = new KBTable(10, "name11", "schema12", 13, List.of(refColumn));
        refDatabase = new KBDatabase(1, "name2", List.of(refTable));
    }

    @Test
    public void testMapperDatabase() {
        final KBDatabaseDTO dto = LayoutInfoMapper.INSTANCE.databaseToDTO(refDatabase);
        Assertions.assertEquals(refDatabase.databaseId(), dto.databaseId());
        Assertions.assertEquals(refDatabase.dbName(), dto.dbName());
        Assertions.assertEquals(refDatabase.tables().size(), dto.tables().size());

        final KBDatabase fromDTO = LayoutInfoMapper.INSTANCE.databaseFromDTO(dto);
        Assertions.assertEquals(dto.databaseId(), fromDTO.databaseId());
        Assertions.assertEquals(dto.dbName(), fromDTO.dbName());
        Assertions.assertEquals(dto.tables().size(), fromDTO.tables().size());
    }

    @Test
    public void testMapperTable() {
        final KBTableDTO dto = LayoutInfoMapper.INSTANCE.tableToDTO(refTable);
        Assertions.assertEquals(refTable.tableId(), dto.tableId());
        Assertions.assertEquals(refTable.tableName(), dto.tableName());
        Assertions.assertEquals(refTable.schema(), dto.schema());
        Assertions.assertEquals(refTable.columns().size(), dto.columns().size());

        final KBTable fromDTO = LayoutInfoMapper.INSTANCE.tableFromDTO(dto);
        Assertions.assertEquals(dto.tableId(), fromDTO.tableId());
        Assertions.assertEquals(dto.tableName(), fromDTO.tableName());
        Assertions.assertEquals(dto.schema(), fromDTO.schema());
        Assertions.assertEquals(dto.columns().size(), fromDTO.columns().size());
        Assertions.assertEquals(dto.tableSelectId(), fromDTO.tableSelectId());
    }

    @Test
    public void testMapperColumn() {
        final KBColumnDTO dto = LayoutInfoMapper.INSTANCE.columnToDTO(refColumn);
        Assertions.assertEquals(refColumn.columnId(), dto.columnId());
        Assertions.assertEquals(refColumn.columnName(), dto.columnName());

        final KBColumn fromDTO = LayoutInfoMapper.INSTANCE.columnFromDTO(dto);
        Assertions.assertEquals(dto.columnId(), fromDTO.columnId());
        Assertions.assertEquals(dto.columnName(), fromDTO.columnName());
    }
}
