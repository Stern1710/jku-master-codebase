package eu.sternbauer.EtlGenerator.KnowledgeBase.TableSelect.dto;

import java.util.List;

/**
 * Data Transfer Object for {@link eu.sternbauer.EtlGenerator.KnowledgeBase.TableSelect.internal.models.KBSelect}.<br />
 * Mapping is handled via {@link eu.sternbauer.EtlGenerator.KnowledgeBase.TableSelect.internal.mapper.TableSelectMapper}.
 * @param tableSelectId
 * @param elements
 */
public record KBSelectDTO(int tableSelectId,
                          List<KBSelectElementDTO> elements) {
}
