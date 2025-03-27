package eu.sternbauer.EtlGenerator.Cli.service;

import eu.sternbauer.EtlGenerator.Configuration.DatabaseConfig;
import eu.sternbauer.EtlGenerator.Configuration.DatabaseProperties;
import eu.sternbauer.EtlGenerator.Generation.service.GenerationService;
import eu.sternbauer.EtlGenerator.KnowledgeBase.Join.dto.KBJoinTableDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.Join.service.JoinService;
import eu.sternbauer.EtlGenerator.KnowledgeBase.LayoutInfo.dto.KBDatabaseDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.LayoutInfo.dto.KBTableDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.LayoutInfo.service.LayoutInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service class for CLI. Handles access to service classes in KnowledgeBase
 * and does additional computation, when applicable.
 */
@Service
public class CliService {
    private final DatabaseProperties databaseProperties;
    private final LayoutInfoService layoutInfoService;
    private final JoinService joinService;
    private final GenerationService generationService;

    @Autowired
    public CliService(DatabaseProperties databaseProperties,
                      LayoutInfoService layoutInfoService,
                      JoinService joinService,
                      GenerationService generationService) {
        this.databaseProperties = databaseProperties;
        this.layoutInfoService = layoutInfoService;
        this.joinService = joinService;
        this.generationService = generationService;
    }
    /*
    ----- CONFIGURATION PROPERTIES -----
     */

    /**
     * Accesses all available database configurations from the datasource key in application.properties
     * @return a list of database configuration
     */
    public List<DatabaseConfig> availableConfigurations() {
        return databaseProperties.datasource();
    }

    /**
     * Access all database configurations which are both present under the datasource key in properties file
     * and in the database itels
     * @return a list of database configuration
     */
    public List<KBDatabaseDTO> getKBDatabaseWithMatchingConfig() {
        Set<String> dbConfigName = availableConfigurations().stream()
                .map(DatabaseConfig::database_name)
                .collect(Collectors.toSet());
        return layoutInfoService.findAllDatabases().stream()
                .filter(db -> dbConfigName.contains(db.dbName()))
                .toList();
    }

    /*
    ----- LAYOUT INFO -----
     */

    /**
     * Accesses all available database configurations from the KnowledgeBase in the database table
     * @return  a list of database configuration
     */
    public List<KBDatabaseDTO> getKBDatabases() {
        return layoutInfoService.findAllDatabases();
    }

    /**
     * Finds all joins for a database in the KnowledgeBase
     * @param dbName name of the database
     * @return a list of joins for the database know in the KnowledgeBase
     */
    public List<KBJoinTableDTO> findJoinsForDatabase(String dbName) {
        Set<Integer> applicableTableIds = layoutInfoService.findAllDatabasesByName(dbName)
                .stream()
                .flatMap(db -> db.tables().stream())
                .map(KBTableDTO::tableId)
                .collect(Collectors.toSet());
        return joinService.getAllJoinTables()
                .stream()
                .filter(jt -> applicableTableIds.contains(jt.table1()) || applicableTableIds.contains(jt.table2()))
                .toList();
    }

    /*
    ----- JOINS -----
     */

    /**
     * Adds a join to the KnowledgeBase
     * @param join Join to be saved
     * @return the saved item from the database with set primary keys
     */
    public KBJoinTableDTO addJoin(KBJoinTableDTO join) {
        return joinService.addJoinTable(join);
    }

    /**
     * Deletes a join from the KnowledgeBase with the given ID. Gives no feedback.
     * @param join_id ID of the join which should be deleted.
     */
    public void deleteJoinById(int join_id) {
        joinService.removeJoinTableById(join_id);
    }

    /*
    ----- GENERATION -----
     */

    /**
     * Generates the ETL statements for the given database to the datatarget.
     * @param sourceName Source database name
     * @return File location of the ETL script.
     */
    public String generateEtl(String sourceName) {
        return generationService.generateEtlLoadingStatements(sourceName);
    }
}
