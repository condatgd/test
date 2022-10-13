package de.berlin.gd.interpreter.domain.interpreter.ports;

import de.berlin.gd.interpreter.domain.interpreter.model.Statements;

public interface ProgramRepoService {
    Statements loadProgram(String name);
}
