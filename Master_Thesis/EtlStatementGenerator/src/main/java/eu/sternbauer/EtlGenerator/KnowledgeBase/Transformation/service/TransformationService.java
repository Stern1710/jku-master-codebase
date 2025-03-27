package eu.sternbauer.EtlGenerator.KnowledgeBase.Transformation.service;

import eu.sternbauer.EtlGenerator.KnowledgeBase.Transformation.dto.KBTransformationMapDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.Transformation.dto.KBTransformationOpDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.Transformation.internal.mapper.TransformationMapper;
import eu.sternbauer.EtlGenerator.KnowledgeBase.Transformation.internal.repository.KBTransformationMapRepo;
import eu.sternbauer.EtlGenerator.KnowledgeBase.Transformation.internal.repository.KBTransformationOpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation to access data, via repositories, for transformation-related tables
 */
@Service
public class TransformationService {
    private final KBTransformationOpRepo kbTransformationOpRepo;
    private final KBTransformationMapRepo kbTransformationMapRepo;

    @Autowired
    public TransformationService(KBTransformationOpRepo kbTransformationOpRepo, KBTransformationMapRepo kbTransformationMapRepo) {
        this.kbTransformationOpRepo = kbTransformationOpRepo;
        this.kbTransformationMapRepo = kbTransformationMapRepo;
    }

    /**
     * Fetches a list of all TransformationOperations from the database and returns them
     * in the form of DTOs. These items include a list of their mappings, as DTOs.
     * @return A List of TransformationOp DTOs
     */
    public List<KBTransformationOpDTO> getTransformationOps() {
        return kbTransformationOpRepo.findAll().stream().map(TransformationMapper.INSTANCE::transformationOpToDTO).toList();
    }

    /**
     * Fetches a list of all TransformationMappings from the database and returns them
     * in the form of DTOs.
     * @return A List of TransformationMap DTOs
     */
    public List<KBTransformationMapDTO> getTransformationMaps() {
        return kbTransformationMapRepo.findAll().stream().map(TransformationMapper.INSTANCE::transformationMapToDTO).toList();
    }
}
