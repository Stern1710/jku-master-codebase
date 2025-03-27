package eu.sternbauer.EtlGenerator.KnowledgeBase.TableSelect.internal.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 * Represents the SelectElement table in the KnowledgeBase and provides mappings for SpringData JDBC.
 * @param tableSelectElementId
 * @param expression
 * @param asName
 * @param comment
 * @param mapping List of all select mappings for this select element
 */
@Table(value = "TableSelectElement", schema = "kb")
public record KBSelectElement(@Column("table_select_element_id") @Id int tableSelectElementId,
                              String expression,
                              @Column("as_name") int asName,
                              @Nullable String comment,
                              @MappedCollection(idColumn = "table_select_element_id", keyColumn = "table_select_element_id") List<KBSelectMap> mapping) { }
