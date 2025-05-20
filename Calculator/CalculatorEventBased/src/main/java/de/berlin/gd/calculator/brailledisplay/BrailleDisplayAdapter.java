package de.berlin.gd.calculator.brailledisplay;

import de.berlin.gd.calculator.domain.ports.outbound_ports.CalculatorOutputPort;
import de.berlin.gd.calculator.domain.ports.outbound_ports.CalculatorPowerOutputPort;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "calculator", name = "viemode", havingValue = "blind")
public class BrailleDisplayAdapter implements CalculatorOutputPort, CalculatorPowerOutputPort {

    private final BrailleDisplay brailleDisplay;

    public BrailleDisplayAdapter(BrailleDisplay brailleDisplay) {
        this.brailleDisplay = brailleDisplay;
    }

    @Override
    public void clear() {
        brailleDisplay.show("");
    }

    @Override
    public void showNumber(int number) {
        brailleDisplay.clear();
        brailleDisplay.show(""+number);
    }

    @Override
    public void providePower(int voltage) {
        System.out.println("LCDDisplayAdapter got power: " + voltage + "V");
    }
}
