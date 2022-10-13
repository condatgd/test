package de.berlin.gd.expression.model;

import de.berlin.gd.expression.model.Expression;

public class StringExpression implements Expression {
    private final String s;

    public StringExpression(String s) {
        this.s = s;
    }

    public String toString() {
        return "\"" + s + "\"";
    }

    public String pureValueString() {
        return s;
    }
    @Override
    public Expression eval(Environment env) {
        return this;
    }

}
