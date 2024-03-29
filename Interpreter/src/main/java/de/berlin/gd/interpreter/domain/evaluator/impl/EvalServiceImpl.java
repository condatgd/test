package de.berlin.gd.interpreter.domain.evaluator.impl;

import de.berlin.gd.interpreter.domain.eval.ExprParser;
import de.berlin.gd.interpreter.domain.eval.impl.ExprParserImpl;
import de.berlin.gd.interpreter.domain.eval.model.Environment;
import de.berlin.gd.interpreter.domain.eval.model.Expression;
import de.berlin.gd.interpreter.domain.eval.model.ParseResult;
import de.berlin.gd.interpreter.domain.evaluator.EvalService;
import de.berlin.gd.interpreter.domain.ports.outbound_ports.ProgramRepoPort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EvalServiceImpl implements EvalService {

    Environment env = new Environment();

    final ProgramRepoPort programRepoService;

    public EvalServiceImpl(ProgramRepoPort programRepoService) {
        this.programRepoService = programRepoService;
    }

    public String eval(String expr) {
        if(expr.equals("new")) {
            env = new Environment();
            return "";
        }
        ExprParser exprParser = new ExprParserImpl();
        Optional<ParseResult> parseResultOptional = exprParser.parse(expr);
        if(parseResultOptional.isPresent()) {
            Expression eval = parseResultOptional.get().getExpr().eval(env);
            return eval.toString();
        } else {
            return "";
        }
    }
}
