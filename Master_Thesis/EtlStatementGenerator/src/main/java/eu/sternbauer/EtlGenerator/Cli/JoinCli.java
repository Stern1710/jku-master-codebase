package eu.sternbauer.EtlGenerator.Cli;

import eu.sternbauer.EtlGenerator.Cli.internal.CliTools;
import eu.sternbauer.EtlGenerator.Cli.service.CliService;
import eu.sternbauer.EtlGenerator.KnowledgeBase.Join.dto.KBJoinColumnDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.Join.dto.KBJoinMapDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.Join.dto.KBJoinTableDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.LayoutInfo.dto.KBColumnDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.LayoutInfo.dto.KBDatabaseDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.LayoutInfo.dto.KBTableDTO;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * CLI access to Join services
 */
@ShellComponent
public class JoinCli {
    private final CliService cliService;
    private final CliTools cliTools;

    public JoinCli(CliService cliService,
                   CliTools cliTools) {
        this.cliService = cliService;
        this.cliTools = cliTools;
    }

    /**
     * Shows all joins for a user-selected database present in the KnowledgeBase
     * @return a formated string with all joins
     */
    @ShellMethod(key="show joins", value="Shows available joins for a given database")
    public String joins() {
        StringBuilder sb = new StringBuilder();
        List<KBDatabaseDTO> dbs = cliService.getKBDatabases();

        String selection = cliTools.getInputForList(dbs.stream().map(KBDatabaseDTO::dbName).toList());

        List<KBJoinTableDTO> joinTables = cliService.findJoinsForDatabase(selection);
        sb.append("Available joins for the database \"").append(selection).append("\"\n");
        joinTables.forEach(jt -> sb.append(" - ").append(jt.toString()).append("\n"));

        return sb.toString();
    }

    /**
     * Deletes a single, user-selected join from a database
     * @return The deleted join
     */
    @ShellMethod(key="delete join", value="Delete a join for a database in the KnowledgeBase")
    public String deleteJoin() {
        List<KBDatabaseDTO> dbs = cliService.getKBDatabases();

        String selection = cliTools.getInputForList(dbs.stream().map(KBDatabaseDTO::dbName).toList());

        List<KBJoinTableDTO> joinTables = cliService.findJoinsForDatabase(selection);
        int removeJoinIndex = cliTools.getInputForListIndex(joinTables.stream().map(KBJoinTableDTO::toString).toList());

        cliService.deleteJoinById(joinTables.get(removeJoinIndex).joinTableId());
        return "Removed " + joinTables.get(removeJoinIndex).toString();
    }

    /**
     * Creates a join dummy, for demonstration purposes
     * @return String representation of the inserted join
     */
    @ShellMethod(key="create join dummy", value="Create a dummy entry to observe behaviour!")
    public String createDummy() {
        KBJoinTableDTO dummyTable = new KBJoinTableDTO(null, "NOPER", 1, 2, "$mappings1",
                List.of(new KBJoinMapDTO(null, "$mappings1", "$colings1 = $colings2",
                        List.of(new KBJoinColumnDTO(null, 1, "$colings1"), new KBJoinColumnDTO(null, 2, "$colings2")))));
        return cliService.addJoin(dummyTable).toString();
    }

    /**
     * Creates a join with input from the user.
     * @return String representation of the inserted join
     */
    @ShellMethod(key="create join", value="Create a join for a database in the KnowledgeBase")
    public String createJoin() {
        List<KBDatabaseDTO> dbs = cliService.getKBDatabases();

        int dbsIndex = cliTools.getInputForListIndex(dbs.stream().map(KBDatabaseDTO::dbName).toList());
        final KBDatabaseDTO db = dbs.get(dbsIndex);
        final List<String> tblNames = db.tables().stream().map(KBTableDTO::tableName).toList();

        System.out.print("Specify the join type (INNER, OUTER, etc): ");
        String type = cliTools.getInputLine();

        System.out.println("Next, please specify the two tables to join");
        System.out.println("Please specify table 1");
        int t1Select = cliTools.getInputForListIndex(tblNames);
        System.out.println("Please specify table 2");
        int t2Select = cliTools.getInputForListIndex(tblNames);

        int t1 = db.tables().get(t1Select).tableId();
        int t2 = db.tables().get(t2Select).tableId();

        List<KBColumnDTO> availableCols = new ArrayList<>(db.tables().get(t1Select).columns());
        availableCols.addAll(db.tables().get(t2Select).columns());

        System.out.print("Specify the mapping: ");
        String condMapping = cliTools.getInputLine();

        long numberOfMaps = condMapping.chars().filter(ch -> ch == '$').count();
        System.out.println("Found " + numberOfMaps + " mappings. Specify them now");

        List<KBJoinMapDTO> joinMappings = new ArrayList<>();
        for (int i=0; i < numberOfMaps; i++) {
            System.out.println("----- Map " + i+1 + "-----");
            System.out.print("Map name (with $): ");
            String mName = cliTools.getInputLine();
            System.out.print("Map condition: ");
            String mCond = cliTools.getInputLine();

            List<KBJoinColumnDTO> cols = new ArrayList<>();
            long numberOfConds = mCond.chars().filter(ch -> ch == '$').count();

            for(int j=0; j < numberOfConds; j++) {
                System.out.println("----- Map " + i+1 + " | Column " + j+1 + "-----");
                System.out.print("Column name (with $): ");
                String cName = cliTools.getInputLine();
                System.out.println("Column id");
                int columnIdSelect = cliTools.getInputForListIndex(availableCols.stream().map(KBColumnDTO::toString).toList());
                int colId = availableCols.get(columnIdSelect).columnId();

                cols.add(new KBJoinColumnDTO(null, colId,  cName));
            }

            joinMappings.add(new KBJoinMapDTO(null, mName, mCond, cols));
        }
        KBJoinTableDTO jt = new KBJoinTableDTO(1000, type, t1, t2, condMapping, joinMappings);
        cliService.addJoin(jt);
        return jt.toString();
    }
}
