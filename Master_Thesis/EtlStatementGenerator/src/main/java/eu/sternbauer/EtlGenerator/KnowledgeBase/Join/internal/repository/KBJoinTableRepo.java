package eu.sternbauer.EtlGenerator.KnowledgeBase.Join.internal.repository;

import eu.sternbauer.EtlGenerator.KnowledgeBase.Join.internal.models.KBJoinTable;
import org.springframework.data.repository.ListCrudRepository;

/**
 * Grants access to JoinTables in the database and loads to JoinTable data items.
 */
public interface KBJoinTableRepo extends ListCrudRepository<KBJoinTable, Long> { }
