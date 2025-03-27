package eu.sternbauer.EtlGenerator.KnowledgeBase.Condition.dto;

/**
 * Data Transfer Object for {@link eu.sternbauer.EtlGenerator.KnowledgeBase.Condition.internal.models.KBConditionMap}.<br />
 * Mapping is handled via {@link eu.sternbauer.EtlGenerator.KnowledgeBase.Condition.internal.mapper.ConditionMapper}
 * @param columnId
 * @param elementName
 */
public record KBConditionMapDTO (
        int columnId,
        String elementName
){ }
