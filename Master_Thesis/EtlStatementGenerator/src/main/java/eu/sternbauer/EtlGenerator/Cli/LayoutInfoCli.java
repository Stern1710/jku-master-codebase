package eu.sternbauer.EtlGenerator.Cli;

import eu.sternbauer.EtlGenerator.Cli.internal.CliTools;
import eu.sternbauer.EtlGenerator.Cli.service.CliService;
import eu.sternbauer.EtlGenerator.Configuration.DatabaseConfig;
import eu.sternbauer.EtlGenerator.KnowledgeBase.LayoutInfo.dto.KBDatabaseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

/**
 * CLI access to layout info services
 */
@ShellComponent
public class LayoutInfoCli {

    private final CliService cliService;
    private final CliTools cliTools;

    @Autowired
    public LayoutInfoCli(CliService cliService,
                         CliTools cliTools) {
        this.cliService = cliService;
        this.cliTools = cliTools;
    }

    /**
     * Displays all database configurations present in application.properties under the datasource key
     * @return Formatted string with the database names
     */
    @ShellMethod(key="show property db", value="Shows database configurations from application.properties")
    public String propertyConfigDb() {

        return "Available configurations in application.properties for sources\n" +
                cliTools.listingWithNumbering(cliService.availableConfigurations().stream().map(DatabaseConfig::database_name).toList());
    }

    /**
     * Displays all database configurations present in the KnowledgeBase
     * @return Formatted string with the database names
     */
    @ShellMethod(key="show kb db", value="Shows database configurations from the KnowledgeBase")
    public String kbConfigDb() {

        return "Available databases in the KnowledgeBase\n" +
                cliTools.listingWithNumbering(cliService.getKBDatabases().stream().map(KBDatabaseDTO::dbName).toList());
    }

    /**
     * Displays all databases whose name is both present in application.properties
     * and the database table in the KnowledgeBase
     * @return Formatted string with the database names
     */
    @ShellMethod(key="show available db", value="Shows available databases for script generation.")
    public String availableConfigsDb() {

        return "Available matching databases in the KnowledgeBase and application properties\n" +
                cliTools.listingWithNumbering(cliService.getKBDatabaseWithMatchingConfig().stream().map(KBDatabaseDTO::dbName).toList());
    }
}
