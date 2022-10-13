package de.berlin.gd.expression.model;

import de.berlin.gd.expression.model.Expression;

public class NumberExpression implements Expression {
    private final double d;

    public NumberExpression(double d) {
        this.d = d;
    }

    public String toString() {
        return "" + d;
    }

    public double getValue() {
        return d;
    }

    @Override
    public Expression eval(Environment env) {
        return this;
    }

}
