package de.berlin.gd.calculator.power;

import de.berlin.gd.calculator.domain.ports.inbound_ports.CalculatorPowerSwitchPort;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class PowerSwitch {
    private boolean state = false;

    private final CalculatorPowerSwitchPort calculatorPowerSwitchPort;

    public PowerSwitch(CalculatorPowerSwitchPort calculatorPowerSwitchPort) {
        this.calculatorPowerSwitchPort = calculatorPowerSwitchPort;
    }

    public void switchPower(boolean on) {
        this.state = on;
        if(on)
            calculatorPowerSwitchPort.on();
        else
            calculatorPowerSwitchPort.off();
    }

}
