package de.berlin.gd.interpreter.domain.interpreter.impl;

import de.berlin.gd.interpreter.domain.evaluator.EvalService;
import de.berlin.gd.interpreter.domain.ports.inbound_ports.InterpreterPort;
import de.berlin.gd.interpreter.domain.interpreter.model.Statement;
import de.berlin.gd.interpreter.domain.interpreter.model.Statements;
import org.springframework.stereotype.Service;

@Service
public class InterpreterServiceImpl implements InterpreterPort {

    EvalService evalService;

    public InterpreterServiceImpl(EvalService evalService) {
        this.evalService = evalService;
    }

    @Override
    public String execute(Statement stmt) {
        return evalService.eval(stmt.getStmtString());
    }

    @Override
    public String execute(Statements statements) {
        statements.getStatements().forEach(s -> evalService.eval(s.getStmtString()));
        return "";
    }
}
