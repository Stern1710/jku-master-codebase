package eu.sternbauer.EtlGenerator.Cli;

import eu.sternbauer.EtlGenerator.Cli.internal.CliTools;
import eu.sternbauer.EtlGenerator.Cli.service.CliService;
import eu.sternbauer.EtlGenerator.Configuration.DatabaseConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

/**
 * CLI access to generation services
 */
@ShellComponent
public class GenerationCli {
    private final Logger logger = LoggerFactory.getLogger(GenerationCli.class);
    private final CliService cliService;
    private final CliTools cliTools;

    @Autowired
    public GenerationCli(CliService cliService, CliTools cliTools) {
        this.cliService = cliService;
        this.cliTools = cliTools;
    }

    /**
     * Generate the ETL scripts for loading from a source. Logs the file location on success.
     * @param srcName optional source name. If no name is presented, a selection dialog will be shown where an available source
     *                can be chosen.
     */
    @ShellMethod(key = "gen etl", value="Generates ETL statements for a selectable source to the default datatarget")
    public void generateETL(@ShellOption(defaultValue = "") String srcName) {
        String sel = srcName.isEmpty()
                ? cliTools.getInputForList(cliService.availableConfigurations().stream().map(DatabaseConfig::database_name).toList())
                : srcName;
        String filePath = cliService.generateEtl(sel);
        if (!filePath.equals("")) {
            logger.info("ETL script located at: {}", filePath);
        } else {
            logger.error("There was a problem during ETL generation. Aborted!");
        }
    }
}
