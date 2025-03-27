package eu.sternbauer.EtlGenerator.KnowledgeBase.Transformation.internal.repository;

import eu.sternbauer.EtlGenerator.KnowledgeBase.Transformation.internal.models.KBTransformationOp;
import org.springframework.data.repository.ListCrudRepository;

/**
 * Grants access to TransformationOps in the database and loads to TransformationOp data items.
 */
public interface KBTransformationOpRepo extends ListCrudRepository<KBTransformationOp, Long> { }
