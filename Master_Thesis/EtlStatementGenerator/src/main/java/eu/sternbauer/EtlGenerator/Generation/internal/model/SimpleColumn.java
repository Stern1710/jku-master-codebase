package eu.sternbauer.EtlGenerator.Generation.internal.model;

/**
 * A minimal subset of all required elements needed from the column elements
 * @param columnId
 * @param columnName
 */
public record SimpleColumn(int columnId, String columnName) { }
