package eu.sternbauer.EtlGenerator.KnowledgeBase.FillOrder.service;

import eu.sternbauer.EtlGenerator.KnowledgeBase.FillOrder.dto.KBFillOrderDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.FillOrder.internal.mapper.FillOrderMapper;
import eu.sternbauer.EtlGenerator.KnowledgeBase.FillOrder.internal.repository.KBFillOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation to access data, via repositories, for the FillOrder table
 */
@Service
public class FillOrderService {
    private final KBFillOrderRepo kbFillOrderRepo;

    @Autowired
    public FillOrderService(KBFillOrderRepo kbFillOrderRepo) {
        this.kbFillOrderRepo = kbFillOrderRepo;
    }

    /**
     * Fetches a list of all FillOrders from the database and returns them
     * in the form of DTOs.
     * @return A List of FillOrder DTOs
     */
    public List<KBFillOrderDTO> getFillOrder() {
        return kbFillOrderRepo.findAll().stream().map(FillOrderMapper.INSTANCE::fillOrderToDTO).toList();
    }
}
