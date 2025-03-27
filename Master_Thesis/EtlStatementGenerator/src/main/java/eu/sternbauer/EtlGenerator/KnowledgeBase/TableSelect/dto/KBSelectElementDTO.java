package eu.sternbauer.EtlGenerator.KnowledgeBase.TableSelect.dto;

import java.util.List;

/**
 * Data Transfer Object for {@link eu.sternbauer.EtlGenerator.KnowledgeBase.TableSelect.internal.models.KBSelectElement}.<br />
 * Mapping is handled via {@link eu.sternbauer.EtlGenerator.KnowledgeBase.TableSelect.internal.mapper.TableSelectMapper}.
 * @param tableSelectElementId
 * @param expression
 * @param asName
 * @param mapping
 */
public record KBSelectElementDTO(int tableSelectElementId,
                                 String expression,
                                 int asName,
                                 List<KBSelectMapDTO> mapping) { }
