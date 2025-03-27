package eu.sternbauer.EtlGenerator.KnowledgeBase.TableSelect.internal.repository;

import eu.sternbauer.EtlGenerator.KnowledgeBase.TableSelect.internal.models.KBSelectElement;
import org.springframework.data.repository.ListCrudRepository;

/**
 * Grants access to SelectElements in the database and loads to SelectElement data items.
 */
public interface KBSelectElementRepo extends ListCrudRepository<KBSelectElement, Integer> { }
