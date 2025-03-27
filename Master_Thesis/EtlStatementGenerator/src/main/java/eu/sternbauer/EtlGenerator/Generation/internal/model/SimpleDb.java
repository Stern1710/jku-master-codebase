package eu.sternbauer.EtlGenerator.Generation.internal.model;

/**
 * A minimal subset of all required elements needed from the database elements
 * @param databaseId
 * @param dbName
 */
public record SimpleDb(int databaseId, String dbName) {}
