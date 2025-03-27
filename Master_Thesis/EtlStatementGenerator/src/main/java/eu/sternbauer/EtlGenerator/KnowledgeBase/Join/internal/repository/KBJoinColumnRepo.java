package eu.sternbauer.EtlGenerator.KnowledgeBase.Join.internal.repository;

import eu.sternbauer.EtlGenerator.KnowledgeBase.Join.internal.models.KBJoinColumn;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Grants access to ConditionMaps in the database and loads to KBConditionMap data items.
 */
public interface KBJoinColumnRepo extends ListCrudRepository<KBJoinColumn, Long> {

    @Modifying
    @Query("UPDATE [kb].[JoinColumn] SET join_map_id = :map_id WHERE join_column_id IN (:pks)")
    void updateMapIdByPrimaryKey(@Param("map_id") int map_id, @Param("pks") List<Integer> primaryKeys);

}
