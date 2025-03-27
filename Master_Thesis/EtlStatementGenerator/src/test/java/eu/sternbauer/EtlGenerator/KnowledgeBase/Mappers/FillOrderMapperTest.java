package eu.sternbauer.EtlGenerator.KnowledgeBase.Mappers;

import eu.sternbauer.EtlGenerator.KnowledgeBase.FillOrder.dto.KBFillOrderDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.FillOrder.internal.mapper.FillOrderMapper;
import eu.sternbauer.EtlGenerator.KnowledgeBase.FillOrder.internal.models.KBFillOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class FillOrderMapperTest {
    private static KBFillOrder refFillOrder;

    @BeforeAll
    public static void setUp() {
        refFillOrder = new KBFillOrder(1, "TableName2", "TableSchema3", 4);
    }

    @Test
    public void testMapperFillOrder() {
        final KBFillOrderDTO dto = FillOrderMapper.INSTANCE.fillOrderToDTO(refFillOrder);
        Assertions.assertEquals(refFillOrder.fillOrderId(), dto.fillOrderId());
        Assertions.assertEquals(refFillOrder.tableName(), dto.tableName());
        Assertions.assertEquals(refFillOrder.tableSchema(), dto.tableSchema());
        Assertions.assertEquals(refFillOrder.orderPriority(), dto.orderPriority());

        final KBFillOrder fromDTO = FillOrderMapper.INSTANCE.fillOrderFromDTO(dto);
        Assertions.assertEquals(dto.fillOrderId(), fromDTO.fillOrderId());
        Assertions.assertEquals(dto.tableName(), fromDTO.tableName());
        Assertions.assertEquals(dto.tableSchema(), fromDTO.tableSchema());
        Assertions.assertEquals(dto.orderPriority(), fromDTO.orderPriority());
    }
}
