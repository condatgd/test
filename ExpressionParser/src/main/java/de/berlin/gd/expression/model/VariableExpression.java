package de.berlin.gd.expression.model;

import de.berlin.gd.expression.model.Expression;

public class VariableExpression implements Expression {
    private final String v;

    public VariableExpression(String v) {
        this.v = v;
    }

    public String toString() {
        return v;
    }

    @Override
    public Expression eval(Environment env) {
        return env.get(v);
    }
}
