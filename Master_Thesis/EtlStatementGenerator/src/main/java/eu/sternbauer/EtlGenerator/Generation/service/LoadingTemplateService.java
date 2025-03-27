package eu.sternbauer.EtlGenerator.Generation.service;

import eu.sternbauer.EtlGenerator.KnowledgeBase.FillOrder.dto.KBFillOrderDTO;
import eu.sternbauer.EtlGenerator.KnowledgeBase.FillOrder.service.FillOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Service
public class LoadingTemplateService {
    private final FillOrderService fillOrderService;

    private final String universityStatement = "INSERT INTO $replaceMe[TemplateDB].[dbo].University\n" +
            "(name_nk)\n" +
            "SELECT DISTINCT name_nk\n" +
            "FROM $replaceMe[TemplateDB].[stage].[Stage_TemplateDB];\n" +
            "\n" +
            "UPDATE std\n" +
            "SET std.dwh_university_id = u.university_id\n" +
            "FROM $replaceMe[TemplateDB].[stage].[Stage_TemplateDB] std\n" +
            "JOIN $replaceMe[TemplateDB].[dbo].[University] u ON std.name_nk = u.name_nk;";

    private final String courseStatement = "INSERT INTO $replaceMe[TemplateDB].[dbo].[Course]\n" +
            "(institute, course_name, ects, source_id)\n" +
            "SELECT DISTINCT institute, course_name, ects, dwh_university_id\n" +
            "FROM $replaceMe[TemplateDB].[stage].[Stage_TemplateDB]\n" +
            "\n" +
            "UPDATE std\n" +
            "SET std.dwh_course_sk = c.course_sk\n" +
            "FROM $replaceMe[TemplateDB].[stage].[Stage_TemplateDB] std\n" +
            "JOIN $replaceMe[TemplateDB].[dbo].[Course] c ON std.institute = c.institute \n" +
            "    AND std.course_name = c.course_name AND std.ects = c.ects\n" +
            "    AND std.dwh_university_id = c.source_id;";

    private final String examStatement = "INSERT INTO $replaceMe[TemplateDB].[dbo].[Exam]\n" +
            "([grade], [year], [semester], [month], source_id)\n" +
            "SELECT DISTINCT grade, [year], semester, [month], dwh_university_id\n" +
            "FROM $replaceMe[TemplateDB].[stage].[Stage_TemplateDB];\n" +
            "\n" +
            "UPDATE std\n" +
            "SET std.dwh_exam_sk = e.exam_sk\n" +
            "FROM $replaceMe[TemplateDB].[stage].[Stage_TemplateDB] std\n" +
            "JOIN $replaceMe[TemplateDB].[dbo].[Exam] e ON std.grade = e.grade AND std.[year] = e.[year] \n" +
            "    AND std.semester = e.semester AND std.[month] = e.[month]\n" +
            "    AND std.dwh_university_id = e.source_id;";

    private final String studentStatement = "INSERT INTO $replaceMe[TemplateDB].[dbo].[Student]\n" +
            "([gender], country, city, source_id)\n" +
            "SELECT DISTINCT gender, country, city, dwh_university_id\n" +
            "FROM $replaceMe[TemplateDB].[stage].[Stage_TemplateDB];\n" +
            "\n" +
            "UPDATE std\n" +
            "SET std.dwh_student_sk = s.student_sk\n" +
            "FROM $replaceMe[TemplateDB].[stage].[Stage_TemplateDB] std\n" +
            "JOIN $replaceMe[TemplateDB].[dbo].[Student] s ON std.gender = s.gender\n" +
            "    AND std.country = s.country AND std.city = s.city\n" +
            "    AND std.dwh_university_id = s.source_id;";

    private final String studyStatement = "INSERT INTO $replaceMe[TemplateDB].[dbo].[Study]\n" +
            "(faculty, study_program, source_id)\n" +
            "SELECT DISTINCT faculty, study_program, dwh_university_id\n" +
            "FROM $replaceMe[TemplateDB].[stage].[Stage_TemplateDB];\n" +
            "\n" +
            "UPDATE std\n" +
            "SET std.dwh_study_sk = s.study_sk\n" +
            "FROM $replaceMe[TemplateDB].[stage].[Stage_TemplateDB] std\n" +
            "JOIN $replaceMe[TemplateDB].[dbo].[Study] s ON std.faculty = s.faculty AND std.study_program = s.study_program\n" +
            "    AND std.dwh_university_id = s.source_id;";

    private final String registrationStatement = "INSERT INTO $replaceMe[TemplateDB].[dbo].[Registration]\n" +
            "(course_sk, exam_sk, student_sk, study_sk)\n" +
            "SELECT DISTINCT dwh_course_sk, dwh_exam_sk, dwh_student_sk, dwh_study_sk\n" +
            "FROM $replaceMe[TemplateDB].[stage].[Stage_TemplateDB];";

    private final Map<String, String> tableStatements;

    @Autowired
    public LoadingTemplateService(FillOrderService fillOrderService) {
        this.fillOrderService = fillOrderService;
        tableStatements = Map.of("dbo.University", universityStatement,
                "dbo.Course", courseStatement,
                "dbo.Exam", examStatement,
                "dbo.Registration", registrationStatement,
                "dbo.Student", studentStatement,
                "dbo.Study", studyStatement);
    }

    public String getStageToTablesStatement(String templateRemoteServerName) {
        StringBuilder sb = new StringBuilder();

        List<String> orderedFills = fillOrderService.getFillOrder().stream()
                .sorted(Comparator.comparingInt(KBFillOrderDTO::orderPriority))
                .map(fo -> fo.tableSchema() + "." + fo.tableName())
                .toList();

        orderedFills.forEach(s -> sb.append("-- Moving data from stage to ").append(s).append("\n")
                .append(tableStatements.get(s).replaceAll("\\$replaceMe", "[" + templateRemoteServerName + "]."))
                .append("\n\n"));

        return sb.toString();
    }
}
