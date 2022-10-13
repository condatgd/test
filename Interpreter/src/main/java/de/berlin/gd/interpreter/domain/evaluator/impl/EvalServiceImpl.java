package de.berlin.gd.interpreter.domain.evaluator.impl;

import de.berlin.gd.interpreter.domain.eval.ExprParser;
import de.berlin.gd.interpreter.domain.eval.model.Environment;
import de.berlin.gd.interpreter.domain.eval.model.Expression;
import de.berlin.gd.interpreter.domain.evaluator.EvalService;
import de.berlin.gd.interpreter.domain.interpreter.ports.ProgramRepoService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EvalServiceImpl implements EvalService {

    Environment env = new Environment();

    final ProgramRepoService programRepoService;

    public EvalServiceImpl(ProgramRepoService programRepoService) {
        this.programRepoService = programRepoService;
    }

    public String eval(String expr) {
        if(expr.equals("new")) {
            env = new Environment();
            return "";
        }
        ExprParser exprParser = new ExprParser();
        Optional<ExprParser.ParseResult> parseResultOptional = exprParser.parse(expr);
        if(parseResultOptional.isPresent()) {
            Expression eval = parseResultOptional.get().getExpr().eval(env);
            return eval.toString();
        } else {
            return "";
        }
    }
}
