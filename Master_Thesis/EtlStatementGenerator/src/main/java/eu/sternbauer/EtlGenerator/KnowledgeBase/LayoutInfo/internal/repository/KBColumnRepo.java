package eu.sternbauer.EtlGenerator.KnowledgeBase.LayoutInfo.internal.repository;

import eu.sternbauer.EtlGenerator.KnowledgeBase.LayoutInfo.internal.models.KBColumn;
import org.springframework.data.repository.ListCrudRepository;

/**
 * Grants access to Columns in the database and loads to Column data items.
 */
public interface KBColumnRepo extends ListCrudRepository<KBColumn, Integer> { }
