package eu.sternbauer.EtlGenerator.KnowledgeBase.TableSelect.internal.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 * Represents the Select table in the KnowledgeBase and provides mappings for SpringData JDBC.
 * @param tableSelectId
 * @param comment
 * @param elements List of all select elements for this select statement
 */
@Table(value = "TableSelect", schema = "kb")
public record KBSelect(@Column("table_select_id") @Id int tableSelectId,
                       @Column("comment") @Nullable String comment,
                       @MappedCollection(idColumn = "table_select_id", keyColumn = "table_select_id") List<KBSelectElement> elements ) {
}
