package eu.sternbauer.EtlGenerator.KnowledgeBase.Join.internal.repository;

import eu.sternbauer.EtlGenerator.KnowledgeBase.Join.internal.models.KBJoinMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Grants access to JoinMaps in the database and loads to JoinMap data items.
 */
public interface KBJoinMapRepo extends ListCrudRepository<KBJoinMap, Long> {

    @Modifying
    @Query("UPDATE [kb].[JoinMap] SET join_table_id = :table_id WHERE join_map_id IN (:pks)")
    void updateTableIdByPrimaryKey(@Param("table_id") int table_id, @Param("pks") List<Integer> primaryKeys);

}
