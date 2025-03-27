package eu.sternbauer.EtlGenerator.KnowledgeBase.TableSelect.service;

import eu.sternbauer.EtlGenerator.KnowledgeBase.TableSelect.dto.KBSelectDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.TableSelect.dto.KBSelectElementDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.TableSelect.dto.KBSelectMapDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.TableSelect.internal.mapper.TableSelectMapper;
import eu.sternbauer.EtlGenerator.KnowledgeBase.TableSelect.internal.repository.KBSelectElementRepo;
import eu.sternbauer.EtlGenerator.KnowledgeBase.TableSelect.internal.repository.KBSelectMapRepo;
import eu.sternbauer.EtlGenerator.KnowledgeBase.TableSelect.internal.repository.KBSelectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation to access data, via repositories, for TableSelect-related tables
 */
@Service
public class TableSelectService {
    private final KBSelectRepo selectRepo;
    private final KBSelectElementRepo elementRepo;
    private final KBSelectMapRepo selectMapRepo;
    @Autowired
    public TableSelectService(KBSelectRepo selectRepo, KBSelectElementRepo elementRepo, KBSelectMapRepo selectMapRepo) {
        this.selectRepo = selectRepo;
        this.elementRepo = elementRepo;
        this.selectMapRepo = selectMapRepo;
    }

    /**
     * Fetches a list of all Selects from the database and returns them
     * in the form of DTOs. These items include a list of their elements and mappings, as DTOs.
     * @return A List of TransformationOp DTOs
     */
    public List<KBSelectDTO> getAllSelects() {
        return selectRepo.findAll().stream().map(TableSelectMapper.INSTANCE::selectToDTO).toList();
    }

    /**
     * Searches the database for a select element by its ID. It will result in either the element, as a DTO, or a null object.
     * The found select includes a list of its elements and mappings, as DTOs.
     * @param id An integer representing the ID by which to find a select element.
     * @return A Select DTO or null, when no element by this ID can be located in the database
     */
    public KBSelectDTO findSelectById(int id) {
        return selectRepo.findById(id).map(TableSelectMapper.INSTANCE::selectToDTO).orElse(null);
    }

    /**
     * Fetches a list of all SelectElements from the database and returns them
     * in the form of DTOs. These items include a list of their mappings, as DTOs.
     * @return A List of SelectElements DTOs
     */
    public List<KBSelectElementDTO> getAllElements() {
        return elementRepo.findAll().stream().map(TableSelectMapper.INSTANCE::selectElementToDTO).toList();
    }

    /**
     * Fetches a list of all SelectMaps from the database and returns them
     * in the form of DTOs.
     * @return A List of SelectMaps DTOs
     */
    public List<KBSelectMapDTO> getAllMaps() {
        return selectMapRepo.findAll().stream().map(TableSelectMapper.INSTANCE::selectMapToDTO).toList();
    }
}
