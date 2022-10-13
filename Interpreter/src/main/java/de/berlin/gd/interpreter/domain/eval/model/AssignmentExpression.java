package de.berlin.gd.interpreter.domain.eval.model;

public class AssignmentExpression implements Expression {

    private final VariableExpression ve;
    private final Expression e;

    public AssignmentExpression(VariableExpression ve, Expression e) {
        this.ve = ve;
        this.e = e;
    }

    public String toString() {
        return ve.toString() + "=" + e.toString();
    }

    @Override
    public Expression eval(Environment env) {
        Expression e1 = e.eval(env);
        env.put(ve.toString(), e1);
        return e1;
    }
}
