package eu.sternbauer.EtlGenerator.KnowledgeBase.LayoutInfo.internal.repository;

import eu.sternbauer.EtlGenerator.KnowledgeBase.LayoutInfo.internal.models.KBDatabase;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

/**
 * Grants access to Databases in the database and loads to Database data items.
 */
public interface KBDatabaseRepo extends ListCrudRepository<KBDatabase, Integer> {
    List<KBDatabase> findKBDatabaseByDbName(String dbName);
}
