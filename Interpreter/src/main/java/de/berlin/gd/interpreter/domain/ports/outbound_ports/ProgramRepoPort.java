package de.berlin.gd.interpreter.domain.ports.outbound_ports;

import de.berlin.gd.interpreter.domain.interpreter.model.Statements;

public interface ProgramRepoPort {
    Statements loadProgram(String name);
}
