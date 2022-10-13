package de.berlin.gd.interpreter.domain.eval.model;

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
