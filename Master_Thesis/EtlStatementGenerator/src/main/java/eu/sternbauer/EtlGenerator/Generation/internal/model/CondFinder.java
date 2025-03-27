package eu.sternbauer.EtlGenerator.Generation.internal.model;

import eu.sternbauer.EtlGenerator.KnowledgeBase.Condition.dto.KBConditionDTO;

/**
 * Helper class to hold data for setting up WHERE conditions when data from concerned tables is used
 * @param cond
 * @param element
 */
public record CondFinder(KBConditionDTO cond, FullLayoutElement element) {}
