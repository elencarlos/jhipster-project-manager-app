package br.gov.mec.polen;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("br.gov.mec.polen");

        noClasses()
            .that()
            .resideInAnyPackage("br.gov.mec.polen.service..")
            .or()
            .resideInAnyPackage("br.gov.mec.polen.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..br.gov.mec.polen.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
