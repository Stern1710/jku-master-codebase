package eu.sternbauer.EtlGenerator.KnowledgeBase.Condition.service;

import eu.sternbauer.EtlGenerator.KnowledgeBase.Condition.dto.KBConditionDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.Condition.dto.KBConditionMapDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.Condition.internal.mapper.ConditionMapper;
import eu.sternbauer.EtlGenerator.KnowledgeBase.Condition.internal.repository.KBConditionMapRepo;
import eu.sternbauer.EtlGenerator.KnowledgeBase.Condition.internal.repository.KBConditionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation to access data, via repositories, for the Condition table
 */
@Service
public class ConditionService {
    private final KBConditionRepo kbConditionRepo;
    private final KBConditionMapRepo conditionMapRepo;


    @Autowired
    public ConditionService(KBConditionRepo kbConditionRepo,
                            KBConditionMapRepo conditionMapRepo) {
        this.kbConditionRepo = kbConditionRepo;
        this.conditionMapRepo = conditionMapRepo;
    }

    /**
     * Fetches a list of all Conditions from the database and returns them
     * in the form of DTOs. These items include a list of their mappings, as DTOs.
     * @return A List of Condition DTOs
     */
    public List<KBConditionDTO> getConditions() {
        return kbConditionRepo.findAll().stream().map(ConditionMapper.INSTANCE::conditionToDTO).toList();
    }

    /**
     * Fetches a list of all ConditionMaps from the database and returns them
     * in the form of DTOs.
     * @return A List of ConditionMaps DTOs
     */
    public List<KBConditionMapDTO> getConditionMaps() {
        return conditionMapRepo.findAll().stream().map(ConditionMapper.INSTANCE::conditionMapToDTO).toList();
    }
}
