package eu.sternbauer.EtlGenerator.KnowledgeBase.LayoutInfo.service;

import eu.sternbauer.EtlGenerator.KnowledgeBase.LayoutInfo.dto.KBColumnDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.LayoutInfo.dto.KBDatabaseDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.LayoutInfo.dto.KBTableDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.LayoutInfo.internal.mapper.LayoutInfoMapper;
import eu.sternbauer.EtlGenerator.KnowledgeBase.LayoutInfo.internal.repository.KBColumnRepo;
import eu.sternbauer.EtlGenerator.KnowledgeBase.LayoutInfo.internal.repository.KBDatabaseRepo;
import eu.sternbauer.EtlGenerator.KnowledgeBase.LayoutInfo.internal.repository.KBTableRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation to access data, via repositories, for LayoutInfo-related tables
 */
@Service
public class LayoutInfoService {
    private final KBDatabaseRepo kbDatabaseRepo;
    private final KBTableRepo kbTableRepo;
    private final KBColumnRepo kbColumnRepo;

    @Autowired
    LayoutInfoService(KBDatabaseRepo kbDatabaseRepo, KBTableRepo kbTableRepo, KBColumnRepo kbColumnRepo) {
        this.kbDatabaseRepo = kbDatabaseRepo;
        this.kbTableRepo = kbTableRepo;
        this.kbColumnRepo = kbColumnRepo;
    }

    /**
     * Fetches a list of all LayoutDatabases from the database and returns them
     * in the form of {@link KBDatabaseDTO}. These items include a list of their tables and columns, as DTOs.
     * @return A List of LayoutInfo DTOs
     */
    public List<KBDatabaseDTO> findAllDatabases() {
        return kbDatabaseRepo.findAll().stream().map(LayoutInfoMapper.INSTANCE::databaseToDTO).toList();
    }

    /**
     * Searches the database for all database by the given name. It will result in a list of databases, as a DTO.
     * The found database includes a list of its tables and columns, as DTOs.
     * @param name A String representing the name by which to find a select element.
     * @return A List of Database DTOs, potentially empty, when nothing with a matching name is present.
     */
    public List<KBDatabaseDTO> findAllDatabasesByName(String name) {
        return kbDatabaseRepo.findKBDatabaseByDbName(name).stream().map(LayoutInfoMapper.INSTANCE::databaseToDTO).toList();
    }

    /**
     * Fetches a list of all LayoutTables from the database and returns them
     * in the form of DTOs. These items include a list of their columns, as DTOs.
     * @return A List of LayoutTable DTOs
     */
    public List<KBTableDTO> findAllTables() {
        return kbTableRepo.findAll().stream().map(LayoutInfoMapper.INSTANCE::tableToDTO).toList();
    }

    /**
     * Fetches a list of all LayoutColumns from the database and returns them
     * in the form of DTOs.
     * @return A List of Column DTOs
     */
    public List<KBColumnDTO> findAllColumns() {
        return kbColumnRepo.findAll().stream().map(LayoutInfoMapper.INSTANCE::columnToDTO).toList();
    }
}
