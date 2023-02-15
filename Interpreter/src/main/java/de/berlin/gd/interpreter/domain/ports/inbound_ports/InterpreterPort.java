package de.berlin.gd.interpreter.domain.ports.inbound_ports;

import de.berlin.gd.interpreter.domain.interpreter.model.Statement;
import de.berlin.gd.interpreter.domain.interpreter.model.Statements;
import de.berlin.gd.interpreter.repo.programs.dbo.ProgramDBO;

public interface InterpreterPort {
    String execute(Statement expr);

    String execute(Statements statements);

}
