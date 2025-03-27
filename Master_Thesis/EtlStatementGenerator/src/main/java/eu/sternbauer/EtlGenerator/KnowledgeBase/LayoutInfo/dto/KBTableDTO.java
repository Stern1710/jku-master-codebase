package eu.sternbauer.EtlGenerator.KnowledgeBase.LayoutInfo.dto;

import java.util.List;

/**
 * Data Transfer Object for {@link eu.sternbauer.EtlGenerator.KnowledgeBase.LayoutInfo.internal.models.KBTable}.<br />
 * Mapping is handled via {@link eu.sternbauer.EtlGenerator.KnowledgeBase.LayoutInfo.internal.mapper.LayoutInfoMapper}.
 * @param tableId
 * @param tableName
 * @param schema
 * @param tableSelectId
 * @param columns
 */
public record KBTableDTO(int tableId,
                         String tableName,
                         String schema,
                         Integer tableSelectId,
                         List<KBColumnDTO> columns) {
}
