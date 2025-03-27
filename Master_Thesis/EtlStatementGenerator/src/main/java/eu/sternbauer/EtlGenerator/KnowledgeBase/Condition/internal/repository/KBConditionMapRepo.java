package eu.sternbauer.EtlGenerator.KnowledgeBase.Condition.internal.repository;

import eu.sternbauer.EtlGenerator.KnowledgeBase.Condition.internal.models.KBConditionMap;
import org.springframework.data.repository.ListCrudRepository;

/**
 * Grants access to ConditionMaps in the database and loads to KBConditionMap data items.
 */
public interface KBConditionMapRepo extends ListCrudRepository<KBConditionMap, Long> { }
