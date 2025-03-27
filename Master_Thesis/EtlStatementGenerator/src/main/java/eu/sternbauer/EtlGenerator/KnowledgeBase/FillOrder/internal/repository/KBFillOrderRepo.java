package eu.sternbauer.EtlGenerator.KnowledgeBase.FillOrder.internal.repository;

import eu.sternbauer.EtlGenerator.KnowledgeBase.FillOrder.internal.models.KBFillOrder;
import org.springframework.data.repository.ListCrudRepository;

/**
 * Grants access to FillOrders in the database and loads to FillOrder data items.
 */
public interface KBFillOrderRepo extends ListCrudRepository<KBFillOrder, Long> { }
