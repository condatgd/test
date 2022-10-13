package de.berlin.gd.interpreter.domain.interpreter;

import de.berlin.gd.interpreter.domain.interpreter.model.Statement;
import de.berlin.gd.interpreter.domain.interpreter.model.Statements;

public interface InterpreterService {
    String execute(Statement expr);

    String execute(Statements statements);
}
