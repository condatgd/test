package de.berlin.gd.calculator.domain.ports.outbound_ports;

public interface CalculatorOutputPort {

    void clear();

    void showNumber(int number);

}
