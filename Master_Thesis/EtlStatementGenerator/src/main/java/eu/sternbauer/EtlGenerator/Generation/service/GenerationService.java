package eu.sternbauer.EtlGenerator.Generation.service;

import eu.sternbauer.EtlGenerator.Configuration.DatabaseConfig;
import eu.sternbauer.EtlGenerator.Configuration.DatabaseProperties;
import eu.sternbauer.EtlGenerator.Generation.internal.mapper.GenerationMapper;
import eu.sternbauer.EtlGenerator.Generation.internal.model.*;
import eu.sternbauer.EtlGenerator.KnowledgeBase.Condition.dto.KBConditionDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.Condition.dto.KBConditionMapDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.Condition.service.ConditionService;
import eu.sternbauer.EtlGenerator.KnowledgeBase.Join.dto.KBJoinColumnDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.Join.dto.KBJoinMapDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.Join.dto.KBJoinTableDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.Join.service.JoinService;
import eu.sternbauer.EtlGenerator.KnowledgeBase.LayoutInfo.dto.KBColumnDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.LayoutInfo.dto.KBDatabaseDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.LayoutInfo.dto.KBTableDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.LayoutInfo.service.LayoutInfoService;
import eu.sternbauer.EtlGenerator.KnowledgeBase.TableSelect.dto.KBSelectDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.TableSelect.dto.KBSelectElementDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.TableSelect.dto.KBSelectMapDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.TableSelect.service.TableSelectService;
import eu.sternbauer.EtlGenerator.KnowledgeBase.Transformation.dto.KBTransformationMapDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.Transformation.dto.KBTransformationOpDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.Transformation.service.TransformationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Generates SQL script files from the information stored inside the KnowledgeBase.
 * It considers linked servers, correct join ordering, where conditions, selects and insert columns.
 * It will produce a file with the source name and place on the file system, which can be executed on a SQL server.
 */
@Service
public class GenerationService {
    private static final Logger logger = LoggerFactory.getLogger(GenerationService.class);

    private final DatabaseProperties dbProperties;
    private final LayoutInfoService layoutInfoService;
    private final TransformationService transformationService;
    private final JoinService joinService;
    private final ConditionService conditionService;
    private final TableSelectService tableSelectService;

    private final GenerationFileService genFileWriter;
    private final LoadingTemplateService loadingTemplateService;

    @Autowired
    public GenerationService(DatabaseProperties dbProperties,
                             LayoutInfoService layoutInfoService,
                             TransformationService transformationService,
                             GenerationFileService genFileWriter,
                             JoinService joinService,
                             ConditionService conditionService,
                             LoadingTemplateService statementToTemplateService,
                             TableSelectService tableSelectService) {
        this.dbProperties = dbProperties;
        this.layoutInfoService = layoutInfoService;
        this.transformationService = transformationService;
        this.genFileWriter = genFileWriter;
        this.joinService = joinService;
        this.conditionService = conditionService;
        this.loadingTemplateService = statementToTemplateService;
        this.tableSelectService = tableSelectService;
    }

    /**
     * Generates ETL statements to load from a given source to specified data target.
     * @param sourceName Name of the source database.
     * @return Path to the generated script file
     */
    public String generateEtlLoadingStatements(String sourceName) {
        logger.info("Generating ETL loading statements for {}", sourceName);
        genFileWriter.reset();

        /*
        Check if Source and Target are available in config
         */
        if (dbProperties.datasource() == null) {
            logger.error("No datasources specified, please add them into the application.properties file");
            return "";
        }
        DatabaseConfig srcConfig = dbProperties.datasource().stream()
                .filter(ds -> ds.database_name().equals(sourceName))
                .findFirst().orElse(null);
        if (srcConfig == null) {
            logger.error("No config found for the given data source name in properties file.");
            return "";
        }

        if (dbProperties.datatarget() == null) {
            logger.error("No datatarget specified, please add them into the application.properties file");
            return "";
        }
        DatabaseConfig dstConfig = dbProperties.datatarget();
        logger.info("Found suitable configurations for data source and data target.");

        /*
        Access information about src/dst and store locally
         */
        List<FullLayoutElement> srcElements = findFullLayoutByDatabaseName(srcConfig.database_name());
        List<FullLayoutElement> dstElements = findFullLayoutByDatabaseName(dstConfig.database_name());
        if (srcElements == null || dstElements == null) {
            logger.error("Quitting ETL generation");
            return "";
        }
        generateShortcodesForTables(srcElements); //Every srcElement gets a shortname, usable for joins later on
        logger.info("Found information in knowledge base about source and destination.");

        /*
        Generate remote server statement
         */
        genFileWriter.addContent("-- Linked server creation for source database");
        genFileWriter.addContent(createLinkedServerStatements(srcConfig));
        genFileWriter.addContent("-- Linked server creation for target database");
        genFileWriter.addContent(createLinkedServerStatements(dstConfig));
        genFileWriter.addContent("GO"); //makes linked server name available for future statements

        logger.info("Generated linked server");

        /*
        Reset the staging area to avoid complications with existing data
         */
        genFileWriter.addContent("\n-- Empty staging table before insert");
        genFileWriter.addContent("DELETE FROM [" + dstConfig.database_name() + "-Server]"
                + ".[TemplateDB].[stage].[Stage_TemplateDB]\nGO\n");

        /*
        Generate some relevant additional data structures which simplify work alter on
         */
        //Keep a map of all src elements with their column number of make matching with the target cols easier / faster
        final Map<Integer, FullLayoutElement> colIdToElement = srcElements.stream()
                .collect(Collectors.toMap(item -> item.column().columnId(), item -> item));
        final Map<Integer, FullLayoutElement> colIdToElementTarget = dstElements.stream()
                .collect(Collectors.toMap(item -> item.column().columnId(), item -> item));

        /*
        Match columns of Target with attributes from source for each target table
         */
        //First, filter all transformation mappings that are not concerned with the chosen source
        final int dbID = srcElements.getFirst().database().databaseId();
        List<KBTransformationOpDTO> relevantTransforms = transformationService.getTransformationOps()
                .stream().filter(op -> op.sourceDatabase() == dbID)
                .toList();

        /*
        Handle INSERT INTO and SELECT Clause at once
         */
        StringBuilder insert = new StringBuilder();
        StringBuilder select = new StringBuilder();

        insert.append("-- Insert into staging table from source\n");
        insert.append("INSERT INTO [").append(dstConfig.database_name()).append("-Server]")
                .append(".[TemplateDB].[stage].[Stage_TemplateDB]")
                .append("\n(");
        select.append("SELECT ");

        List<String> insertElements = new ArrayList<>();
        List<String> selectElements = new ArrayList<>();
        for (KBTransformationOpDTO transform : relevantTransforms) {
            insertElements.add(colIdToElementTarget.get(transform.columnId()).column().columnName());

            String expr = transform.expression();
            for (KBTransformationMapDTO mapping : transform.mappings()) {
                expr = expr.replace(mapping.elementName(), colIdToElement.get(mapping.columnId()).getShortnamePath());
            }
            selectElements.add(expr);
        }

        insert.append(String.join(", ", insertElements)).append(")");
        select.append(String.join(", ", selectElements)).append("\n");

        genFileWriter.addContent(insert.toString());
        genFileWriter.addContent(select.toString());

        logger.info("Generated insert and select statement(s)");


        /*
        Generate join code, if applicable, from join finder elements
         */
        // Get all necessary joins for the given select statement
        final Queue<JoinFinder> joinFinders = findJoins(srcElements, colIdToElement);

        StringBuilder joinString = new StringBuilder("FROM ");
        Map<Integer, StringBuilder> tableJoinConditions = new LinkedHashMap<>();

        if (joinFinders.isEmpty()) {
            //Special and most basic case: No joins needed to fill table, just generate a FROM statement with the one required table
            FullLayoutElement t1Elem = srcElements.stream().findFirst().orElse(null);
            if (t1Elem == null) {
                logger.error("Failed to find an element for the FROM statement. Please make sure that the source database" +
                        " information is available in the KnowledgeBase");
                return "";
            }
            joinString.append(t1Elem.getFullTablePath()).append(" ").append(t1Elem.table().getBracketShortname());
        } else {
            //Special treatment for first element, where only required = offered has to be matched, as available is empty
            while (!joinFinders.isEmpty()) {
                JoinFinder jf = joinFinders.poll();
                List<Integer> offeredTables = List.of(jf.getT1Offer(), jf.getT2Offer());

                //This software does not support self-referencing joins, just drop these items
                if (jf.getT1Offer() == jf.getT2Offer()) continue;

                if (!new HashSet<>(offeredTables).containsAll(jf.addedRequirements())) {
                    joinFinders.add(jf);
                    continue;
                }

                //Found the first possible join, write with FROM-statement
                KBJoinTableDTO join = jf.join();

                FullLayoutElement t1Elem = findElementWithTable(srcElements, jf.getT1Offer());
                FullLayoutElement t2Elem = findElementWithTable(srcElements, jf.getT2Offer());
                if (t1Elem == null || t2Elem == null) {
                    logger.error("Error with matching joins to tables! Relevant items are{} and{}.\nPlease check the data validity", t1Elem, t2Elem);
                    continue;
                }

                //Check here if custom table select is used
                // If yes, insert this instead of the fullTablePath along with the short name
                // If no, proceed as usual
                if (t1Elem.table().getTableSelectId() == null) {
                    tableJoinConditions.put(jf.getT1Offer(), new StringBuilder().append(t1Elem.getFullTablePath()).append(" ").append(t1Elem.table().getBracketShortname()));
                } else {
                    tableJoinConditions.put(jf.getT1Offer(), concatTableSelect(colIdToElement, t1Elem).append(t1Elem.table().getBracketShortname()));
                }

                String onCond = concatJoinCondition(colIdToElement, join);
                if (t2Elem.table().getTableSelectId() == null) {
                    tableJoinConditions.put(jf.getT2Offer(), new StringBuilder().append(join.joinType()).append(" JOIN ").append(t2Elem.getFullTablePath()).append(" ").append(t2Elem.table().getBracketShortname())
                            .append(" ON ").append(onCond));
                } else {
                    tableJoinConditions.put(jf.getT2Offer(), new StringBuilder().append(join.joinType()).append(" JOIN ").append(concatTableSelect(colIdToElement, t2Elem))
                            .append(" ").append(t2Elem.table().getBracketShortname()).append(" ON ").append(onCond));
                }

                break; //Break here to exit loop as one element has been found
            }

            //For all other elements, check against the available list
            int repeatCounter = 0;
            while (!joinFinders.isEmpty()) {
                if (repeatCounter >= joinFinders.size()) { //Protection to not run into an endless loop when elements are exhausted
                    logger.warn("Repeat counter exceeded limit, breaking join findings!\n{}", joinFinders);
                    break;
                }
                JoinFinder jf = joinFinders.poll();
                KBJoinTableDTO join = jf.join();
                Set<Integer> keys = tableJoinConditions.keySet();

                //If both t1 and t2 are already added, find the last inserted one and add the condition
                if (tableJoinConditions.containsKey(jf.getT1Offer()) && tableJoinConditions.containsKey(jf.getT2Offer())) {
                    int lastKey = -1;
                    //As the linked hash map preserves the insertion order, iteration is valid
                    for (Integer key : tableJoinConditions.keySet()) {
                        //As we know that both keys exist, no additional checks needed
                        if (key.equals(jf.getT1Offer())) {
                            lastKey = jf.getT2Offer();
                            break;
                        }
                        if (key.equals(jf.getT2Offer())) {
                            lastKey = jf.getT1Offer();
                            break;
                        }
                    }
                    String onCond = concatJoinCondition(colIdToElement, join);
                    tableJoinConditions.get(lastKey).append(" AND ").append(onCond);
                    continue;

                } else if (!(keys.contains(jf.getT1Offer()) || keys.contains(jf.getT2Offer()))
                        // t1, t2 not available OR added requirements not fulfilled
                        || !keys.containsAll(jf.addedRequirements())) {
                    joinFinders.add(jf);
                    repeatCounter++;
                    continue;
                }

                repeatCounter = 0;
                //Find out which table is newly introduced, and which isn't -> Only print the new one
                int tableToJoin = keys.contains(jf.getT1Offer()) ? jf.getT2Offer() : jf.getT1Offer();
                FullLayoutElement tElem = findElementWithTable(srcElements, tableToJoin);
                if (tElem == null) {
                    logger.error("Error with matching joins to tables! Relevant item is tableId={}.\nPlease check the data validity", tableToJoin);
                    continue;
                }

                // Also check here if custom table select is used
                // If yes, insert this instead of the fullTablePath along with the short name
                // If no, proceed as usual
                String onCond = concatJoinCondition(colIdToElement, join);
                if (tElem.table().getTableSelectId() == null) {
                    tableJoinConditions.put(tableToJoin, new StringBuilder().append(join.joinType()).append(" JOIN ").append(tElem.getFullTablePath()).append(" ").append(tElem.table().getBracketShortname())
                            .append(" ON ").append(onCond));
                } else {
                    tableJoinConditions.put(tableToJoin, new StringBuilder().append(join.joinType()).append(" JOIN ").append(concatTableSelect(colIdToElement, tElem)).append(" ").append(tElem.table().getBracketShortname())
                            .append(" ON ").append(onCond));
                }
            }
        }
        tableJoinConditions.forEach((_, v) -> joinString.append(v.toString()).append("\n"));
        genFileWriter.addContent(joinString.toString());

        logger.info("Generated FROM and join statements");

        /*
        Find where clauses applicable to statement
         */
        Set<Integer> available = tableJoinConditions.keySet();
        Set<Integer> colIds = srcElements.stream().map(e -> e.column().columnId()).collect(Collectors.toSet());

        List<KBConditionDTO> conditions = conditionService.getConditions();
        List<CondFinder> applicableElems = conditions.stream()
                .filter(c -> colIds.contains(c.columnId()))
                .map(c -> new CondFinder(c, colIdToElement.get(c.columnId())))
                .filter(cf -> available.contains(cf.element().table().getTableId()))
                .toList();

        if (!applicableElems.isEmpty()) {
            List<String> clauses = new ArrayList<>();
            for (CondFinder elem : applicableElems) {
                String s = elem.cond().expression().replace("$col", elem.element().getShortnamePath());
                for (KBConditionMapDTO mapping : elem.cond().mappings()) {
                    s = s.replace(mapping.elementName(), colIdToElement.get(mapping.columnId()).getShortnamePath());
                }
                clauses.add("(" + s + ")");
            }
            genFileWriter.addContent("WHERE " + String.join("\n\tAND ", clauses));
        }
        genFileWriter.addContent("\n");

        logger.info("Generated WHERE clauses");

        /*
        Append loading statements from stage to TemplateDB tables
         */
        genFileWriter.addContent(loadingTemplateService.getStageToTablesStatement(dstConfig.database_name() + "-Server"));
        logger.info("Generated stage to table loading statements");

        /*
        Write content to file, cleanup, exit
         */
        logger.info("Finished generating ETL statements");
        genFileWriter.setFileName(sourceName + "_load_script");
        try {
            return genFileWriter.writeOut();
        } catch (IOException e) {
            logger.error("Writing to the file via genFileWriter failed, sorry :(");
        }
        return "";
    }

    /* -----------
    HELPER METHODS for finding elements
     ------------- */

    /**
     * Finds all tables and columns inside the KnowledgeBase for a given database name
     * and transforms the entries.
     * @param dbName Name of the database for which the layout items should be generated
     * @return A list of {@link FullLayoutElement} elements which do all belong the the same database under the passed name
     */
    private List<FullLayoutElement> findFullLayoutByDatabaseName(String dbName) {
        KBDatabaseDTO dbInfo;
        try {
            dbInfo = layoutInfoService.findAllDatabasesByName(dbName).getFirst();
        } catch (NoSuchElementException e) {
            logger.error("No database information found for '{}' in KnowledgeBase.", dbName);
            return null;
        }
        logger.info("Found suitable database information for {}", dbName);

        List<FullLayoutElement> srcElements = new ArrayList<>();
        SimpleDb srcDb = GenerationMapper.INSTANCE.simpleDbFromDTO(dbInfo);
        for (KBTableDTO table : dbInfo.tables()) {
            SimpleTable srcTbl = GenerationMapper.INSTANCE.simpleTableFromDTO(table);
            for (KBColumnDTO col : table.columns()) {
                SimpleColumn srcCol = GenerationMapper.INSTANCE.simpleColumnFromDTO(col);
                srcElements.add(new FullLayoutElement(srcDb, srcTbl, srcCol));
            }
        }
        if (srcElements.isEmpty()) {
            logger.error("Database information could not accessed, please verify that it is complete and correctly formatted" +
                    " inside the database");
        }
        return srcElements;
    }

    /**
     * Finds the first table element with a given ID from the list of layout elements.
     * @param elements Elements which are searched
     * @param tableId The relevant table ID
     * @return A FullLayoutElement with the table ID or null, if no table with said ID is found.
     */
    private FullLayoutElement findElementWithTable(List<FullLayoutElement> elements, int tableId) {
        return elements.stream().filter(e -> e.table().getTableId() == tableId).findFirst().orElse(null);
    }

    /**
     * Finds all joins from the list of layout elements by accessing all column IDs and then extracting the join information.
     * @param srcElements Elements for which the joins should be found
     * @param colIdToElement A mapping from column IDs to layout elements
     * @return A {@link Queue}, typically implemented as a LinkedList, will all found Joins in the form of {@link JoinFinder}
     */
    private Queue<JoinFinder> findJoins(List<FullLayoutElement> srcElements, Map<Integer, FullLayoutElement> colIdToElement) {
        final Queue<JoinFinder> joinFinders = new LinkedList<>();
        final Set<Integer> tableIds = srcElements.stream()
                .map(e -> e.table().getTableId()).collect(Collectors.toSet());

        final List<KBJoinTableDTO> joins = joinService.getAllJoinTables()
                .stream().filter(j -> tableIds.contains(j.table1()) && tableIds.contains(j.table2()))
                .toList();

        for (KBJoinTableDTO join : joins) {
            List<Integer> required = join.mappings().stream()
                    .flatMap(j -> j.columns().stream())
                    .map(c -> colIdToElement.get(c.columnId()).table().getTableId())
                    .filter(i -> i != join.table1() && i != join.table2())
                    .toList();
            joinFinders.add(new JoinFinder(join, required));
        }

        return joinFinders;
    }

    /* -----------
    HELPER METHODS for creating strings
     ------------- */

    /**
     * Generates the linked server statements, using available configs from application.properties.
     * @param config {@link DatabaseConfig} read from the application.properties file
     * @return String containing the linked server statements plus authentification.
     */
    private String createLinkedServerStatements(DatabaseConfig config) {

        return "IF EXISTS (SELECT 1 FROM sys.servers WHERE name = '" + config.database_name() + "-Server')\n" +
                "BEGIN\n" +
                "\tEXEC sp_dropserver '" + config.database_name() + "-Server', 'droplogins';\n" +
                "END\n\n" +
                "EXEC sp_addlinkedserver\n" +
                "\t@server='" + config.database_name() + "-Server',\n" +
                "\t@srvproduct = '',\n" +
                "\t@provider = 'SQLNCLI',\n" +
                "\t@datasrc = '" + config.url() + "';\n\n" +
                "EXEC sp_addlinkedsrvlogin\n" +
                "\t@rmtsrvname='" + config.database_name() + "-Server',\n" +
                "\t@useself = 'FALSE',\n" +
                "\t@locallogin = NULL,\n" +
                "\t@rmtuser = '" + config.username() + "',\n" +
                "\t@rmtpassword = '" + config.password() + "';\n";
    }

    /**
     * Handles the mapping of join condition elements, to replace the placeholder name with the actual shortcode + column name
     * @param colIdToElement Mapping of columns to elements
     * @param join The join for which the mapping is handled.
     * @return The mapping string with table shortcodes and column names instead of the placeholders.
     */
    private String concatJoinCondition(Map<Integer, FullLayoutElement> colIdToElement, KBJoinTableDTO join) {
        String onCond = join.condition();
        for (KBJoinMapDTO jMap : join.mappings()) {
            String mapString = jMap.condition();
            for (KBJoinColumnDTO jCol : jMap.columns()) {
                mapString = mapString.replace(jCol.elementName(), colIdToElement.get(jCol.columnId()).getShortnamePath());
            }
            onCond = onCond.replace(jMap.elementName(), mapString);
        }
        return onCond;
    }

    /**
     * Handles the mapping ofo custom table select elements, replace the placeholder name with the actual shortcode + column name
     * @param colIdToElement Mapping of columns to elements
     * @param t1Elem layout element which has a custom select on the table element.
     * @return a StringBuilder with the mapping, containing table shortcodes and column names instead of the placeholders.
     */
    private StringBuilder concatTableSelect(Map<Integer, FullLayoutElement> colIdToElement, FullLayoutElement t1Elem) {
        KBSelectDTO tSelect = tableSelectService.findSelectById(t1Elem.table().getTableSelectId());
        StringBuilder sb = new StringBuilder().append("(SELECT ");
        for (KBSelectElementDTO element : tSelect.elements()) {
            String s = element.expression();
            s = s.replace("$as_col", colIdToElement.get(element.asName()).column().columnName());
            for (KBSelectMapDTO map : element.mapping()) {
                s = s.replace(map.elementName(), colIdToElement.get(map.columnId()).column().columnName());
            }
            sb.append(s).append(" FROM ").append(t1Elem.getFullTablePath()).append(")");
        }
        return sb;
    }

    /**
     * Generates shortcodes for each table. To avoid duplicated names that could result in errors, it looks into the
     * already existing names and adds one additional character from the table name. If the whole name is already taken,
     * the process will start repeating the table name character by character until a suitable shortcode is found.
     * @param elements A list of layout elements which need their shortcuts set.
     */
    private void generateShortcodesForTables(List<FullLayoutElement> elements) {
        final Set<String> usedShortCodes = new HashSet<>();

        //Step 1, get all tables unique table elements
        final Set<SimpleTable> tables = elements.stream()
                .map(FullLayoutElement::table)
                .collect(Collectors.toSet());

        // Step 2, generate short code for each table
        for (SimpleTable table : tables) {
            int subIndex = 1;
            String tName = table.getTableName();
            String proposedName = tName.substring(0, subIndex);

            while (usedShortCodes.contains(proposedName)) {
                subIndex++;
                proposedName += tName.substring(subIndex - 1, subIndex);

                if (subIndex >= tName.length()) {
                    subIndex = 1; //Reset index to just add the string from the beginning to achieve uniqueness
                }
            }
            usedShortCodes.add(proposedName);
            table.setShortname(proposedName);
        }
    }
}
