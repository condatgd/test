package de.berlin.gd.calculator.domain.ports.outbound_ports;

public interface CalculatorPowerOutputPort {
    void providePower(int voltage);
}
