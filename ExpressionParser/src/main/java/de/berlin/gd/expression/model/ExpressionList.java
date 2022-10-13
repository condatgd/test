package de.berlin.gd.expression.model;

import de.berlin.gd.expression.model.Expression;

import java.util.List;
import java.util.stream.Collectors;

public class ExpressionList implements Expression {
    private final List<Expression> l;

    public ExpressionList(List<Expression> l) {
        this.l = l;
    }

    public String toString() {
        return l.toString();
    }

    public List<Expression> getExpressions() {
        return l;
    }

    @Override
    public Expression eval(Environment env) {
        return new ExpressionList(l.stream().map(e -> e.eval(env)).collect(Collectors.toList()));
    }
}
