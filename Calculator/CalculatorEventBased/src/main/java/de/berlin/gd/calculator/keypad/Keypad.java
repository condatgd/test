package de.berlin.gd.calculator.keypad;

import de.berlin.gd.calculator.domain.model.Code;
import de.berlin.gd.calculator.domain.model.CodeEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class Keypad  {

    private final ApplicationEventPublisher publisher;

    public Keypad(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void click(char enteredChar) {
        Code codeToEnter = Arrays.stream(Code.values())
                .filter(code -> code.getChar() == enteredChar)
                .findFirst()
                .orElse(Code.EQ);
        publisher.publishEvent(new CodeEvent(codeToEnter));
    }

}
