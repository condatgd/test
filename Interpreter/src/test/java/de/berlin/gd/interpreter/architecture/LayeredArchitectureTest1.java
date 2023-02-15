package de.berlin.gd.interpreter.architecture;

import com.tngtech.archunit.lang.ArchRule;
import org.junit.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.onionArchitecture;

public class LayeredArchitectureTest1 extends ArchitectureTest {

    @Test
    public void onion_architecture_is_respected() {
        onionArchitecture()
                .domainModels("..eval.model..", "..interpreter.model..")
                .domainServices("..domain..")
                .applicationServices("..api..")
                .adapter("persistence", "..repo..")
                .adapter("rest", "..api..")
                .check(classes);
    }

    @Test
    public void checkDomainPorts() {
        ArchRule portsShouldBeInterfaces = classes().that().resideInAPackage("..ports..").should().beInterfaces();
        ArchRule portsDependency =         classes().that().resideInAPackage("..ports..").should().dependOnClassesThat().resideInAPackage("..domain..");

        portsShouldBeInterfaces.check(classes);
        portsDependency.check(classes);
    }


}