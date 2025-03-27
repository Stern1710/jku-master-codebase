package eu.sternbauer.EtlGenerator.KnowledgeBase.TableSelect.internal.repository;

import eu.sternbauer.EtlGenerator.KnowledgeBase.TableSelect.internal.models.KBSelectMap;
import org.springframework.data.repository.ListCrudRepository;

/**
 * Grants access to SelectMaps in the database and loads to SelectMaps data items.
 */
public interface KBSelectMapRepo extends ListCrudRepository<KBSelectMap, Integer> {}
