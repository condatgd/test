package de.berlin.gd.interpreter.domain.eval.model;

import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.DoubleBinaryOperator;

public interface Expression {
    Expression eval(Environment env);

    default Expression apply(String f, Expression pExpr, Environment env) {
        switch (f) {
            case "+": {
                DoubleBinaryOperator add = Double::sum;
                NumberExpression e1 = mathOperation(pExpr, env, add);
                if (e1 != null) return e1;
            }
            case "-": {
                DoubleBinaryOperator minus = (a, b) -> a - b;
                NumberExpression e1 = mathOperation(pExpr, env, minus);
                if (e1 != null) return e1;
            }
            case "*": {
                DoubleBinaryOperator mult = (a, b) -> a * b;
                NumberExpression e1 = mathOperation(pExpr, env, mult);
                if (e1 != null) return e1;
            }
            case "/": {
                DoubleBinaryOperator div = (a, b) -> a / b;
                NumberExpression e1 = mathOperation(pExpr, env, div);
                if (e1 != null) return e1;
            }
            case "<": {
                if(pExpr instanceof ExpressionList) {
                    OpDDB less = (a, b) -> a < b;
                    BooleanExpression e1 = boolOperation(pExpr, env, less);
                    if (e1 != null) return e1;
                }
            }
            case ">": {
                if(pExpr instanceof ExpressionList) {
                    OpDDB greater = (a, b) -> a > b;
                    BooleanExpression e1 = boolOperation(pExpr, env, greater);
                    if (e1 != null) return e1;
                }
            }
            case ".": {
                if(pExpr instanceof ExpressionList) {
                    Expression expr = pExpr.eval(env);
                    ExpressionList eList = (ExpressionList) expr;
                    BinaryOperator<Expression> concatExpressions = (x1, x2) -> new StringExpression(x1.pureValueString()+x2.pureValueString());
                    return eList.getExpressions().stream().reduce(new StringExpression(""), concatExpressions);
                }
            }
            case "if": {
                if(pExpr instanceof ExpressionList && ((ExpressionList) pExpr).getExpressions().size()==3) {
                    ExpressionList eList = (ExpressionList) pExpr;
                    List<Expression> expressions = eList.getExpressions();
                    Expression condition = expressions.get(0);
                    Expression evalCondition = condition.eval(env);
                    if(evalCondition instanceof BooleanExpression) {
                        BooleanExpression evalCondition1 = (BooleanExpression) evalCondition;
                        if(evalCondition1.getValue()) {
                            return expressions.get(1).eval(env);
                        } else {
                            return expressions.get(2).eval(env);
                        }
                    }
                }
            }
            case "not": {
                if(pExpr instanceof ExpressionList && ((ExpressionList) pExpr).getExpressions().size()==1) {
                    ExpressionList eList = (ExpressionList) pExpr;
                    List<Expression> expressions = eList.getExpressions();
                    Expression condition = expressions.get(0);
                    Expression evalCondition = condition.eval(env);
                    if(evalCondition instanceof BooleanExpression) {
                        BooleanExpression evalCondition1 = (BooleanExpression) evalCondition;
                        return new BooleanExpression(!evalCondition1.getValue());
                    }
                }
            }
            case "function": {
                if(pExpr instanceof ExpressionList && ((ExpressionList) pExpr).getExpressions().size()==2) {
                    ExpressionList eList = (ExpressionList) pExpr;
                    List<Expression> expressions = eList.getExpressions();
                    Expression head = expressions.get(0);
                    Expression body = expressions.get(1);
                    if(head instanceof FunctionCallExpression) {
                        FunctionCallExpression head1 = (FunctionCallExpression) head;
                        String fName = head1.getF();
                        FunctionDefinition functionDefinition = new FunctionDefinition(head, body);
                        env.put("fn_"+fName, functionDefinition);
                        return functionDefinition;
                    }
                }
            }
            default:
                Expression fn = env.get("fn_" + f);
                if(fn instanceof FunctionDefinition) {
                    FunctionDefinition fn1 = (FunctionDefinition) fn;
                    Expression head = fn1.getHead();
                    Expression body = fn1.getBody();
                    env.pushNewEnv();
                    createHeadBindings(head, pExpr, env);
                    Expression evalBody = body.eval(env);
                    env.popEnv();
                    return evalBody;
                }
                return new FunctionCallExpression(f,pExpr.eval(env));
        }
    }

    private NumberExpression mathOperation(Expression pExpr, Environment env, DoubleBinaryOperator op) {
        if(pExpr instanceof ExpressionList) {
            Expression expr = pExpr.eval(env);
            ExpressionList eList = (ExpressionList) expr;
            List<Expression> expressions = eList.getExpressions();
            Expression e1 = expressions.get(0);
            Expression e2 = expressions.get(1);
            return new NumberExpression(op.applyAsDouble(((NumberExpression) e1).getValue(),((NumberExpression) e2).getValue()));
        }
        return null;
    }

    private BooleanExpression boolOperation(Expression pExpr, Environment env, OpDDB op) {
        if(pExpr instanceof ExpressionList) {
            Expression expr = pExpr.eval(env);
            ExpressionList eList = (ExpressionList) expr;
            List<Expression> expressions = eList.getExpressions();
            Expression e1 = expressions.get(0);
            Expression e2 = expressions.get(1);
            return new BooleanExpression(op.applyAsDouble(((NumberExpression) e1).getValue(),((NumberExpression) e2).getValue()));
        }
        return null;
    }

    default void createHeadBindings(Expression head, Expression pExpr, Environment env) {
        if(head instanceof FunctionCallExpression && pExpr instanceof ExpressionList) {
            FunctionCallExpression headFunCall = (FunctionCallExpression) head;
            ExpressionList exprList = (ExpressionList) pExpr;
            Expression headExpr = headFunCall.getE();
            List<Expression> headExpressions = ((ExpressionList) headExpr).getExpressions();
            List<Expression> argExpressions = exprList.getExpressions();
            if(headExpressions.size()==argExpressions.size()) {
                for(int i=0; i<headExpressions.size(); i++) {
                    env.put(
                            headExpressions.get(i).pureValueString(),
                            argExpressions.get(i).eval(env)
                    );
                }
            }
        }
    }

    default String pureValueString() {
        return this.toString();
    }

    @FunctionalInterface
    interface OpDDB {
        boolean applyAsDouble(double left, double right);
    }

}
