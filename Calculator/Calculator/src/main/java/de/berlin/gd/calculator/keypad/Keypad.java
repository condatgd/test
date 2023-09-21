package de.berlin.gd.calculator.keypad;

import de.berlin.gd.calculator.domain.model.Code;
import de.berlin.gd.calculator.domain.ports.inbound_ports.CalculatorInputPort;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class Keypad  {

    private final CalculatorInputPort calculatorInputPort;

    public Keypad(CalculatorInputPort inputDevice) {
        this.calculatorInputPort = inputDevice;
    }

    public void click(char enteredChar) {
        Code codeToEnter = Arrays.stream(Code.values())
                .filter(code -> code.getChar() == enteredChar)
                .findFirst()
                .orElse(Code.EQ);
        calculatorInputPort.enterCode(codeToEnter);
    }

}
