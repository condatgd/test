package de.berlin.gd.interpreter.architecture;

import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.Test;
import org.springframework.stereotype.Service;

public class ApplicationComponentsTest extends ArchitectureTest {

    @Test
    public void serviceImplementationsShouldBeAnnotatedWithServiceAnnotation() {
        ArchRule rule = ArchRuleDefinition.classes()
                .that().haveSimpleNameEndingWith("ServiceImpl")
                .should().beAnnotatedWith(Service.class);
        rule.check(classes);
    }

    @Test
    public void shouldResideOutsideTheApplicationLayer() {
        ArchRule rule = ArchRuleDefinition.classes()
                .that().haveSimpleNameEndingWith("Service")
                .should().beInterfaces();
        rule.check(classes);
    }
}