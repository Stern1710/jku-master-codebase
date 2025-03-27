package eu.sternbauer.EtlGenerator.KnowledgeBase.Transformation.internal.repository;

import eu.sternbauer.EtlGenerator.KnowledgeBase.Transformation.internal.models.KBTransformationMap;
import org.springframework.data.repository.ListCrudRepository;

/**
 * Grants access to TransformationMaps in the database and loads to TransformationMap data items.
 */
public interface KBTransformationMapRepo extends ListCrudRepository<KBTransformationMap, String> { }
