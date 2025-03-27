package eu.sternbauer.EtlGenerator.KnowledgeBase.Condition.dto;

import java.util.List;

/**
 * Data Transfer Object for {@link eu.sternbauer.EtlGenerator.KnowledgeBase.Condition.internal.models.KBCondition}.<br />
 * Mapping is handled via {@link eu.sternbauer.EtlGenerator.KnowledgeBase.Condition.internal.mapper.ConditionMapper}
 * @param columnConditionId
 * @param columnId
 * @param expression
 * @param mappings
 */
public record KBConditionDTO (
        int columnConditionId,
        int columnId,
        String expression,
        List<KBConditionMapDTO> mappings
){ }