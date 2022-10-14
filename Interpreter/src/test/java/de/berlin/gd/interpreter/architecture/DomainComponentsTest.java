package de.berlin.gd.interpreter.architecture;

import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.Test;

public class DomainComponentsTest extends ArchitectureTest {

    @Test
    public void domainClassesShouldOnlyBeAccessedByOtherDomainClassesOrTheApplicationLayer() {
        ArchRule rule = ArchRuleDefinition.classes()
                .that().resideInAPackage(DOMAIN_LAYER_PACKAGES)
                .should().onlyBeAccessed().byAnyPackage(DOMAIN_LAYER_PACKAGES, APPLICATION_LAYER_PACKAGES);
        rule.check(classes);
    }

}