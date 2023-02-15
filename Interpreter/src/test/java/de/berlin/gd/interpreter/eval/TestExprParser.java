package de.berlin.gd.interpreter.eval;

import de.berlin.gd.interpreter.domain.eval.ExprParser;
import de.berlin.gd.interpreter.domain.eval.impl.ExprParserImpl;
import de.berlin.gd.interpreter.domain.eval.model.ParseResult;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestExprParser {

    @Test
    void test1() {
        ExprParser exprParser = new ExprParserImpl();
        Optional<ParseResult> c = exprParser.parse("1");
        System.out.println(c.toString());
        assertTrue(c.isPresent());
    }

    @Test
    void test2() {
        ExprParser exprParser = new ExprParserImpl();
        Optional<ParseResult> v = exprParser.parse("\"hallo\"");
        System.out.println(v.toString());
        assertTrue(v.isPresent());
    }

    @Test
    void test3() {
        ExprParser exprParser = new ExprParserImpl();
        Optional<ParseResult> e = exprParser.parse("1+\"hallo\"");
        System.out.println(e.toString());
        assertTrue(e.isPresent());
    }

    @Test
    void test4() {
        ExprParser exprParser = new ExprParserImpl();
        Optional<ParseResult> e = exprParser.parse("(123+a)*(\"sgfwreg\"+67)");
        System.out.println(e.toString());
        assertTrue(e.isPresent());
    }

    @Test
    void test5() {
        ExprParser exprParser = new ExprParserImpl();
        Optional<ParseResult> e = exprParser.parse("f((a+b)*c,y*4)");
        System.out.println(e.toString());
        assertTrue(e.isPresent());
    }

    @Test
    void test6() {
        ExprParser exprParser = new ExprParserImpl();
        Optional<ParseResult> e = exprParser.parse("x=g(f((a+b)*c,y*4,g,h))");
        System.out.println(e.toString());
        assertTrue(e.isPresent());
    }

    @Test
    void test7() {
        ExprParser exprParser = new ExprParserImpl();
        Optional<ParseResult> e = exprParser.parse("x=list(1,2,3)");
        System.out.println(e.toString());
        assertTrue(e.isPresent());
    }

    @Test
    void test8() {
        ExprParser exprParser = new ExprParserImpl();
        Optional<ParseResult> e = exprParser.parse("print(\"Hallo\")");
        System.out.println(e.toString());
        assertTrue(e.isPresent());
    }

    @Test
    void test9() {
        ExprParser exprParser = new ExprParserImpl();
        Optional<ParseResult> e = exprParser.parse("if(b,1+3,2+7)");
        System.out.println(e.toString());
        assertTrue(e.isPresent());
    }

    @Test
    void test10() {
        ExprParser exprParser = new ExprParserImpl();
        Optional<ParseResult> e = exprParser.parse("true");
        System.out.println(e.toString());
        assertTrue(e.isPresent());
    }

    @Test
    void test11() {
        ExprParser exprParser = new ExprParserImpl();
        Optional<ParseResult> e = exprParser.parse("n>10");
        System.out.println(e.toString());
        assertTrue(e.isPresent());
    }

}
