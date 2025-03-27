package eu.sternbauer.EtlGenerator.KnowledgeBase.Condition.internal.repository;

import eu.sternbauer.EtlGenerator.KnowledgeBase.Condition.internal.models.KBCondition;
import org.springframework.data.repository.ListCrudRepository;

/**
 * Grants access to Conditions in the database and loads to KBCondition data items.
 */
public interface KBConditionRepo extends ListCrudRepository<KBCondition, Integer> { }
