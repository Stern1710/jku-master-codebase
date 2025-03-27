package eu.sternbauer.EtlGenerator.KnowledgeBase.LayoutInfo.internal.repository;

import eu.sternbauer.EtlGenerator.KnowledgeBase.LayoutInfo.internal.models.KBTable;
import org.springframework.data.repository.ListCrudRepository;

/**
 * Grants access to Tables in the database and loads to Table data items.
 */
public interface KBTableRepo extends ListCrudRepository<KBTable, Integer> {
}
