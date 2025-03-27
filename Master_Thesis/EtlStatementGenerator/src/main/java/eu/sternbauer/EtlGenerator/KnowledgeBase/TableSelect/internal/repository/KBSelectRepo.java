package eu.sternbauer.EtlGenerator.KnowledgeBase.TableSelect.internal.repository;

import eu.sternbauer.EtlGenerator.KnowledgeBase.TableSelect.internal.models.KBSelect;
import org.springframework.data.repository.ListCrudRepository;

/**
 * Grants access to Selects in the database and loads to Select data items.
 */
public interface KBSelectRepo extends ListCrudRepository<KBSelect, Integer> { }
