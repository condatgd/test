package de.berlin.gd.interpreter.eval;

import de.berlin.gd.interpreter.domain.eval.ExprParser;
import de.berlin.gd.interpreter.domain.eval.impl.ExprParserImpl;
import de.berlin.gd.interpreter.domain.eval.model.Environment;
import de.berlin.gd.interpreter.domain.eval.model.ParseResult;
import org.junit.jupiter.api.Test;

import java.util.Optional;


public class TestExprEval {

    @Test
    void test1() {
        ExprParser exprParser = new ExprParserImpl();
        Environment env = new Environment();
        Optional<ParseResult> ass1 = exprParser.parse("a=1+2");
        Optional<ParseResult> ass2 = exprParser.parse("a=2+2");
        Optional<ParseResult> sum = exprParser.parse("a+3");

        ass1.get().getExpr().eval(env);
        ass2.get().getExpr().eval(env);

        System.out.println(env);
        System.out.println(sum.get().getExpr().eval(env));
    }

    @Test
    void test2() {
        ExprParser exprParser = new ExprParserImpl();
        Environment env = new Environment();
        Optional<ParseResult> f = exprParser.parse("f(1+3,3+4)");

        System.out.println(f.get().getExpr().eval(env));
    }

    @Test
    void test3() {
        ExprParser exprParser = new ExprParserImpl();
        Environment env = new Environment();
        Optional<ParseResult> f = exprParser.parse("\"a\".1.\"hallo\"");

        System.out.println(f.get().getExpr().eval(env));
    }

    @Test
    void test4() {
        ExprParser exprParser = new ExprParserImpl();
        Environment env = new Environment();
        Optional<ParseResult> ass1 = exprParser.parse("b=true");
        ass1.get().getExpr().eval(env);
        Optional<ParseResult> f = exprParser.parse("if(b,1+3,2+7)");
        System.out.println(f.get().getExpr().eval(env));
        f = exprParser.parse("if(not(not(not(b))),1+3,2+7)");
        System.out.println(f.get().getExpr().eval(env));
    }

    @Test
    void test5() {
        ExprParser exprParser = new ExprParserImpl();
        Environment env = new Environment();

        Optional<ParseResult> f = exprParser.parse("function(fac(n),if((1-1)<n,n*fac(n-1),1))");
        System.out.println(f.get().getExpr().eval(env));
        System.out.println(env);

        Optional<ParseResult> call = exprParser.parse("x=if(true,fac(5),fac(10000))");
        System.out.println(call.get().getExpr().eval(env));
        System.out.println(env);
    }

    @Test
    void test6() {
        ExprParser exprParser = new ExprParserImpl();
        Environment env = new Environment();

        Optional<ParseResult> f = exprParser.parse("10<20");
        System.out.println(f.get().getExpr().eval(env));

        Optional<ParseResult> g = exprParser.parse("20<10");
        System.out.println(g.get().getExpr().eval(env));
    }

}
