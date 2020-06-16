package com.ruckuswireless.dpsk.networkconfig;

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
            .importPackages("com.ruckuswireless.dpsk.networkconfig");

        noClasses()
            .that()
            .resideInAnyPackage("com.ruckuswireless.dpsk.networkconfig.service..")
            .or()
            .resideInAnyPackage("com.ruckuswireless.dpsk.networkconfig.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.ruckuswireless.dpsk.networkconfig.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
