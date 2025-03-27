package eu.sternbauer.EtlGenerator.archunit.KnowledgeBase;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class KBNotAccessingOthersTest {
    private static JavaClasses importedClass;

    @BeforeAll
    static void setup() {
        importedClass = new ClassFileImporter()
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
                .importPackages("eu.sternbauer.EtlGenerator");
    }

    @Test
    void ensureKBIsNotImportingFromGeneration() {
        ArchRule rule = ArchRuleDefinition.noClasses()
                .that().resideInAPackage("..KnowledgeBase..")
                .should().accessClassesThat().resideInAnyPackage("..Generation..")
                .because("KnowledgeBase must not depend upon Generation");
        rule.check(importedClass);
    }
}
