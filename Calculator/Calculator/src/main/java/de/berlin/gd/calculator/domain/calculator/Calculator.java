package de.berlin.gd.calculator.domain.calculator;

import de.berlin.gd.calculator.domain.alu.ALU;
import de.berlin.gd.calculator.domain.model.Code;
import de.berlin.gd.calculator.domain.model.CodeEvent;
import de.berlin.gd.calculator.domain.ports.inbound_ports.CalculatorInputPort;
import de.berlin.gd.calculator.domain.ports.inbound_ports.CalculatorPowerSwitchPort;
import de.berlin.gd.calculator.domain.ports.outbound_ports.CalculatorOutputPort;
import de.berlin.gd.calculator.domain.ports.outbound_ports.CalculatorPowerOutputPort;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class Calculator implements CalculatorInputPort, CalculatorPowerSwitchPort {

    final ALU alu;

    final CalculatorOutputPort calculatorOutputPort;
    final CalculatorPowerOutputPort calculatorPowerOutputPort;
    private final ApplicationEventPublisher publisher;

    String line = "";

    boolean hasPower = false;

    public Calculator(ALU alu, CalculatorOutputPort calculatorOutputPort, CalculatorPowerOutputPort calculatorPowerOutputPort, ApplicationEventPublisher publisher) {
        this.alu = alu;
        this.calculatorOutputPort = calculatorOutputPort;
        this.calculatorPowerOutputPort = calculatorPowerOutputPort;
        this.publisher = publisher;
    }

    @Override
    public void enterCode(Code code) {
        publisher.publishEvent(new CodeEvent(code));
        if(hasPower) {
            if (code.equals(Code.EQ)) {
                int result = alu.evaluate(line);
                calculatorOutputPort.showNumber(result);
                line = "";
            } else {
                line += code.getChar();
            }
        }
    }

    @Override
    public void on() {
        hasPower = true;
        calculatorPowerOutputPort.providePower(5);
        calculatorOutputPort.showNumber(0);

    }

    @Override
    public void off() {
        hasPower = false;
        calculatorPowerOutputPort.providePower(0);
        calculatorOutputPort.clear();
    }
}
