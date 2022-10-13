package de.berlin.gd.interpreter.domain.eval.model;

public class FunctionCallExpression implements Expression {

    private final String f;
    private final Expression e1;

    public FunctionCallExpression(String f, Expression e1) {
        this.f = f;
        this.e1 = e1;
    }

    public String getF() {
        return f;
    }

    public Expression getE() {
        return e1;
    }

    public String toString() {
        return f + "(" + e1.toString() + ")";
    }

    @Override
    public Expression eval(Environment env) {
        return apply(f, e1, env);
    }
}
