package eu.sternbauer.EtlGenerator.KnowledgeBase.Join.service;

import eu.sternbauer.EtlGenerator.KnowledgeBase.Join.dto.KBJoinColumnDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.Join.dto.KBJoinMapDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.Join.dto.KBJoinTableDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.Join.internal.mapper.JoinMapper;
import eu.sternbauer.EtlGenerator.KnowledgeBase.Join.internal.models.KBJoinColumn;
import eu.sternbauer.EtlGenerator.KnowledgeBase.Join.internal.models.KBJoinMap;
import eu.sternbauer.EtlGenerator.KnowledgeBase.Join.internal.models.KBJoinTable;
import eu.sternbauer.EtlGenerator.KnowledgeBase.Join.internal.repository.KBJoinColumnRepo;
import eu.sternbauer.EtlGenerator.KnowledgeBase.Join.internal.repository.KBJoinMapRepo;
import eu.sternbauer.EtlGenerator.KnowledgeBase.Join.internal.repository.KBJoinTableRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service implementation to access data, via repositories, for Join-related tables
 */
@Service
public class JoinService {
    private final KBJoinTableRepo kbJoinTableRepo;
    private final KBJoinMapRepo kbJoinMapRepo;
    private final KBJoinColumnRepo kbJoinColumnRepo;

    @Autowired
    public JoinService(KBJoinTableRepo kbJoinTableRepo, KBJoinMapRepo kbJoinMapRepo, KBJoinColumnRepo kbJoinColumnRepo) {
        this.kbJoinTableRepo = kbJoinTableRepo;
        this.kbJoinMapRepo = kbJoinMapRepo;
        this.kbJoinColumnRepo = kbJoinColumnRepo;
    }

    /**
     * Fetches a list of all JoinTables from the database and returns them
     * in the form of DTOs. These items include a list of their mappings and columns, as DTOs.
     * @return A List of JoinTable DTOs
     */
    public List<KBJoinTableDTO> getAllJoinTables() {
        return kbJoinTableRepo.findAll().stream().map(JoinMapper.INSTANCE::joinTableToDTO).toList();
    }

    /**
     * Fetches a list of all JoinMaps from the database and returns them
     * in the form of DTOs. These items include a list of their columns, as DTOs.
     * @return A List of JoinMap DTOs
     */
    public List<KBJoinMapDTO> getAllJoinMaps() {
        return kbJoinMapRepo.findAll().stream().map(JoinMapper.INSTANCE::joinMapToDTO).toList();
    }

    /**
     * Fetches a list of all JoinColumns from the database and returns them
     * in the form of DTOs.
     * @return A List of JoinColumn DTOs
     */
    public List<KBJoinColumnDTO> getAllJoinColumns() {
        return kbJoinColumnRepo.findAll().stream().map(JoinMapper.INSTANCE::joinColumnToDTO).toList();
    }

    /**
     * Saves a JoinTable to the KnowledgeBase. Handles updates of Map and Column foreign keys' to their respective parents.
     * @param joinTableDTO Element to be saved
     * @return The KBJoinTableDTO element from the save statement.
     */
    @Transactional
    public KBJoinTableDTO addJoinTable(KBJoinTableDTO joinTableDTO) {
        KBJoinTable saved = kbJoinTableRepo.save(JoinMapper.INSTANCE.joinTableFromDTO(joinTableDTO));

        kbJoinMapRepo.updateTableIdByPrimaryKey(saved.joinTableId(),
                saved.mappings().stream().map(KBJoinMap::joinMapId).toList());
        saved.mappings().forEach(jm -> {
            kbJoinColumnRepo.updateMapIdByPrimaryKey(jm.joinMapId(),
                    jm.columns().stream().map(KBJoinColumn::joinColumnId).toList());
        });

        return JoinMapper.INSTANCE.joinTableToDTO(saved);
    }

    @Transactional
    public void removeJoinTableById(long joinTableId) {
        kbJoinTableRepo.deleteById(joinTableId);
    }
}
