package de.berlin.gd.asm;

import de.berlin.gd.expression.model.*;

public class Compiler {

    public void exprToAsm(Expression expr) {
        if(expr instanceof NumberExpression) {
            asm("push #" + ((NumberExpression)expr).getValue());
        }
        if(expr instanceof InfixExpression) {
            Expression e1 = ((InfixExpression)expr).getE1();
            Expression e2 = ((InfixExpression)expr).getE2();
            exprToAsm(e1);
            exprToAsm(e2);
            String f = ((InfixExpression)expr).getOp();
            asm("push #" + f);
            asm("jsr eval");
        }
        if(expr instanceof ExpressionList) {
            ((ExpressionList)expr).getExpressions().forEach(e -> {
                exprToAsm(e);
            });
        }
        if(expr instanceof FunctionCallExpression) {
            Expression e = ((FunctionCallExpression)expr).getE();
            exprToAsm(e);
            String f = ((FunctionCallExpression)expr).getF();
            asm("push #" + f);
            asm("jsr eval");
        }
    }

    private void asm(String s) {
        System.out.println(s);
    }


}
