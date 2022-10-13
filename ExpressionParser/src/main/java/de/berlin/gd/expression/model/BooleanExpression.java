package de.berlin.gd.expression.model;

public class BooleanExpression implements Expression {
    private final boolean b;

    public BooleanExpression(boolean b) {
        this.b = b;
    }

    public String toString() {
        return (""+b).toUpperCase();
    }

    public boolean getValue() {
        return b;
    }

    @Override
    public Expression eval(Environment env) {
        return this;
    }

}
