package de.berlin.gd.calculator.domain.ports.inbound_ports;

public interface CalculatorPowerSwitchPort {
    void on();
    void off();
}
