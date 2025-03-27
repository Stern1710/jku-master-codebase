package eu.sternbauer.EtlGenerator.Configuration;

/**
 * Representation of a whole database configuration, as needed by Spring to connect to KnowledgeBase
 * or to be used for generating linked server statements by the generation service
 * @param url Location of the sql server; This can be both a JDBC connection string or address,port combination
 * @param username database login username
 * @param password database login password
 * @param driver_class_name Spring data JDBC attribute, not relevant for the generation service
 * @param database_name Database name
 */
public record DatabaseConfig(
        String url,
        String username,
        String password,
        String driver_class_name,
        String database_name
) {}
