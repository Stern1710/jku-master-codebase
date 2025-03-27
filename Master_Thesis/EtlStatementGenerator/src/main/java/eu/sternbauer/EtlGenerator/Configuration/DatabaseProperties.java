package eu.sternbauer.EtlGenerator.Configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * Provides access to all database-related configurations for the
 * generation service and Spring Data JDBC to work with. It uses {@link DatabaseConfig} to
 * encapsulate the relevant data.<br />
 * The prefix for Spring to automatically fill in these fields is {@code eu.sternbauer.db-config}
 * @param knowledgebase Configuration for the knowledge base location.
 * @param datatarget Configuration for the data transfer target, used as the destination in generation
 * @param datasource List of configurations for a data source, used in generation for the selected database
 */
@ConfigurationProperties(prefix = "eu.sternbauer.db-config")
public record DatabaseProperties(DatabaseConfig knowledgebase,
                                 DatabaseConfig datatarget,
                                 List<DatabaseConfig> datasource) {
}
