package de.berlin.gd.expression;

import de.berlin.gd.asm.Compiler;
import de.berlin.gd.expression.model.Environment;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;


public class TestExprCompile {

    @Test
    void test1() {
        ExprParser exprParser = new ExprParser();
        Compiler compiler = new Compiler();
        Environment env = new Environment();
        compiler.exprToAsm(exprParser.parse("12+13").get().getExpr());
    }

    @Test
    void test2() {
        ExprParser exprParser = new ExprParser();
        Compiler compiler = new Compiler();
        Environment env = new Environment();
        compiler.exprToAsm(exprParser.parse("f(12+13,123)").get().getExpr());
        gosub();
    }

    private void gosub() {
        List<String> l = null;
        for (String s : l) {

        }
        System.out.println("123");
    }

}
