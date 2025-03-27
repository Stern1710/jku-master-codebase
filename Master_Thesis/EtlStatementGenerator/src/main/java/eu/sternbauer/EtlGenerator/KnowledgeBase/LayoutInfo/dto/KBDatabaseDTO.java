package eu.sternbauer.EtlGenerator.KnowledgeBase.LayoutInfo.dto;

import java.util.List;

/**
 * Data Transfer Object for {@link eu.sternbauer.EtlGenerator.KnowledgeBase.LayoutInfo.internal.models.KBDatabase}.<br />
 * Mapping is handled via {@link eu.sternbauer.EtlGenerator.KnowledgeBase.LayoutInfo.internal.mapper.LayoutInfoMapper}.
 * @param databaseId
 * @param dbName
 * @param tables
 */
public record KBDatabaseDTO(int databaseId,
                            String dbName,
                            List<KBTableDTO> tables) {
}
