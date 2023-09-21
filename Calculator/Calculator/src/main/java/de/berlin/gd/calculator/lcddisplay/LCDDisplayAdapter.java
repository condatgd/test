package de.berlin.gd.calculator.lcddisplay;

import de.berlin.gd.calculator.domain.ports.outbound_ports.CalculatorOutputPort;
import de.berlin.gd.calculator.domain.ports.outbound_ports.CalculatorPowerOutputPort;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "calculator", name = "viemode", havingValue = "seeing")
public class LCDDisplayAdapter implements CalculatorOutputPort, CalculatorPowerOutputPort {

    private final LCDDisplay lcdDisplay;

    public LCDDisplayAdapter(LCDDisplay lcdDisplay) {
        this.lcdDisplay = lcdDisplay;
    }

    @Override
    public void clear() {
        lcdDisplay.show("");
    }

    @Override
    public void showNumber(int number) {
        lcdDisplay.clear();
        lcdDisplay.show(""+number);
    }

    @Override
    public void providePower(int voltage) {
        System.out.println("LCDDisplayAdapter got power: " + voltage + "V");
    }
}
