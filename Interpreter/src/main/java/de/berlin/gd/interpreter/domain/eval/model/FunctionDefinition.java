package de.berlin.gd.interpreter.domain.eval.model;

public class FunctionDefinition implements Expression {
    private final Expression head;
    private final Expression body;

    public FunctionDefinition(Expression head, Expression body) {
        this.head = head;
        this.body = body;
    }

    public Expression getHead() {
        return head;
    }

    public Expression getBody() {
        return body;
    }

    @Override
    public Expression eval(Environment env) {
        return null;
    }

    public String toString() {
        return "FN " + head + " -> " + body;
    }

}
