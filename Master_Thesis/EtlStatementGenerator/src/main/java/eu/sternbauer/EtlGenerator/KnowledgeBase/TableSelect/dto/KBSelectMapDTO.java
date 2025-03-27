package eu.sternbauer.EtlGenerator.KnowledgeBase.TableSelect.dto;

/**
 * Data Transfer Object for {@link eu.sternbauer.EtlGenerator.KnowledgeBase.TableSelect.internal.models.KBSelectMap}.<br />
 * Mapping is handled via {@link eu.sternbauer.EtlGenerator.KnowledgeBase.TableSelect.internal.mapper.TableSelectMapper}.
 * @param columnId
 * @param elementName
 */
public record KBSelectMapDTO(int columnId,
                             String elementName) {}
