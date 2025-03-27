package eu.sternbauer.EtlGenerator.archunit.Cli;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CliServiceInternalTest {
    private static JavaClasses importedClass;

    @BeforeAll
    static void setUp() {
        importedClass = new ClassFileImporter()
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
                .importPackages("eu.sternbauer.EtlGenerator");
    }

    @Test
    void ensureCliInternalStaysInternal() {
        ArchRule rule = ArchRuleDefinition.classes()
                .that().resideInAnyPackage("..Cli.internal..")
                .should().onlyBeAccessed().byClassesThat().resideInAnyPackage("..Cli..");
        rule.check(importedClass);
    }
}
