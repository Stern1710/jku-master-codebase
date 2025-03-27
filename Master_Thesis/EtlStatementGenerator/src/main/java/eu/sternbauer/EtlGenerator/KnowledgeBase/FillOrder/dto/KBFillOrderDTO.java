package eu.sternbauer.EtlGenerator.KnowledgeBase.FillOrder.dto;

/**
 * Data Transfer Object for {@link eu.sternbauer.EtlGenerator.KnowledgeBase.FillOrder.internal.models.KBFillOrder}.<br />
 * Mapping is handled via {@link eu.sternbauer.EtlGenerator.KnowledgeBase.FillOrder.internal.mapper.FillOrderMapper}
 * @param fillOrderId
 * @param tableName
 * @param tableSchema
 * @param orderPriority
 */
public record KBFillOrderDTO (int fillOrderId,
                             String tableName, String tableSchema,
                             int orderPriority){
}
