package eu.sternbauer.EtlGenerator.archunit.KnowledgeBase;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class KBSiblingTest {

    private static final String rootPackageName = "eu.sternbauer.EtlGenerator.KnowledgeBase";
    private static long numberOfDots;
    private static JavaClasses importedClassKB;

    @BeforeAll
    static void setup() {
        importedClassKB = new ClassFileImporter().withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS).importPackages(rootPackageName);
        numberOfDots = rootPackageName.chars().filter(ch -> ch == '.').count();
    }

    @Test
    void siblingPackageNotAccessing() {
        Set<String> pkgNames = importedClassKB.stream()
                .filter(c -> c.getPackageName().chars().filter(ch -> ch == '.').count() > numberOfDots)
                .map(c -> c.getPackageName().substring(rootPackageName.length() + 1))
                .map(c -> c.contains(".") ? c.substring(0, c.indexOf('.')) : c)
                .distinct()
                .map(c -> rootPackageName + "." + c + "..")
                .collect(Collectors.toSet());

        pkgNames.forEach(p -> {
            String[] compare = pkgNames.stream().filter(c -> !c.equals(p)).toArray(String[]::new);
            Arrays.stream(compare).forEach(c -> {
                ArchRule rule = ArchRuleDefinition.noClasses()
                        .that().resideInAPackage(p)
                        .should().accessClassesThat().resideInAnyPackage(compare)
                        .because("Classes in " + p + " should not access any of the siblings");
                rule.check(importedClassKB);
            });
        });

    }
}
