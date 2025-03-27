package eu.sternbauer.EtlGenerator.KnowledgeBase.LayoutInfo.internal.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

/**
 * Represents the Database table in the KnowledgeBase and provides mappings for SpringData JDBC.
 * @param databaseId
 * @param dbName Database name, relevant when generating statements for a given database. Must match the name provided
 *               in application properties to
 * @param tables List of all tables associated with this database
 */
@Table(value = "DatabaseInformation", schema = "kb")
public record KBDatabase(
        @Id @Column("database_id") int databaseId,
        @Column("db_name") String dbName,
        @MappedCollection(idColumn = "database_id", keyColumn = "database_id") List<KBTable> tables
) { }
