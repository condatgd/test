package de.berlin.gd.interpreter.domain.eval.model;

import java.util.List;

public class InfixExpression implements Expression {
    private final Expression e1;
    private final String op;
    private final Expression e2;

    public InfixExpression(Expression e1, String op, Expression e2) {
        this.e1 = e1;
        this.op = op;
        this.e2 = e2;
    }

    public String toString() {
        return "(" + e1.toString() + op + e2.toString() + ")";
    }

    public Expression getE1() {
        return e1;
    }

    public String getOp() {
        return op;
    }

    public Expression getE2() {
        return e2;
    }


    @Override
    public Expression eval(Environment env) {
        return apply(op, new ExpressionList(List.of(e1,e2)), env);
    }


}
