package de.berlin.gd.interpreter.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.BeforeClass;

public abstract class ArchitectureTest {
    static final String APPLICATION_LAYER_PACKAGES = "de.berlin.gd.interpreter..";
    static final String DOMAIN_LAYER_PACKAGES = "de.berlin.gd.interpreter.domain..";

    static JavaClasses classes;

    @BeforeClass
    public static void setUp() {
        classes = new ClassFileImporter()
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_ARCHIVES)
                .importPackages("de.berlin.gd.interpreter");
    }
}