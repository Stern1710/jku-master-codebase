package eu.sternbauer.EtlGenerator.archunit.KnowledgeBase;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class KBInternalTest {

    private static JavaClasses importedClass;

    @BeforeAll
    static void setup() {
        importedClass = new ClassFileImporter()
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
                .importPackages("eu.sternbauer.EtlGenerator");
    }

    @Test
    void ensureConditionInternalStaysInternal() {
        ArchRule rule = ArchRuleDefinition.classes()
                .that().resideInAPackage("..Condition.internal..")
                .should().onlyBeAccessed().byClassesThat().resideInAPackage("..Condition..");
        rule.check(importedClass);
    }

    @Test
    void ensureJoinInternalStaysInternal() {
        ArchRule rule = ArchRuleDefinition.classes()
                .that().resideInAPackage("..Join.internal..")
                .should().onlyBeAccessed().byClassesThat().resideInAPackage("..Join..");

        rule.check(importedClass);
    }

    @Test
    void ensureLayoutInfoInternalStaysInternal() {
        ArchRule rule = ArchRuleDefinition.classes()
                .that().resideInAPackage("..LayoutInfo.internal..")
                .should().onlyBeAccessed().byClassesThat().resideInAPackage("..LayoutInfo..");

        rule.check(importedClass);
    }

    @Test
    void ensureTableSelectInternalStaysInternal() {
        ArchRule rule = ArchRuleDefinition.classes()
                .that().resideInAPackage("..TableSelect.internal..")
                .should().onlyBeAccessed().byClassesThat().resideInAPackage("..TableSelect..");

        rule.check(importedClass);
    }

    @Test
    void ensureTransformationInternalStaysInternal() {
        ArchRule rule = ArchRuleDefinition.classes()
                .that().resideInAPackage("..Transformation.internal..")
                .should().onlyBeAccessed().byClassesThat().resideInAPackage("..Transformation..");

        rule.check(importedClass);
    }

    @Test
    void ensureFillOrderInternalStaysInternal() {
        ArchRule rule = ArchRuleDefinition.classes()
                .that().resideInAPackage("..FillOrder.internal..")
                .should().onlyBeAccessed().byClassesThat().resideInAPackage("..FillOrder..");

        rule.check(importedClass);
    }
}
