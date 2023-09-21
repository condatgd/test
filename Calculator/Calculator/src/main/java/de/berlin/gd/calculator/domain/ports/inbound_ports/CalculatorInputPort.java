package de.berlin.gd.calculator.domain.ports.inbound_ports;

import de.berlin.gd.calculator.domain.model.Code;

public interface CalculatorInputPort {
    void enterCode(Code code);
}
