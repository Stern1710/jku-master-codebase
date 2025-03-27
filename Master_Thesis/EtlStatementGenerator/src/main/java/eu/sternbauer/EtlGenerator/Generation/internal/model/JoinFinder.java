package eu.sternbauer.EtlGenerator.Generation.internal.model;

import eu.sternbauer.EtlGenerator.KnowledgeBase.Join.dto.KBJoinTableDTO;

import java.util.List;

/**
 * Helper class to hold data for setting up JOIN conditions when entries are located in the KnowledgeBase
 * @param join
 * @param addedRequirements
 */
public record JoinFinder(KBJoinTableDTO join, List<Integer> addedRequirements) {
    public int getT1Offer() {
        return join.table1();
    }
    public int getT2Offer() {
        return join.table2();
    }
}
