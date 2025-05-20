package de.berlin.gd.calculator.domain.alu;

import net.objecthunter.exp4j.ExpressionBuilder;
import org.springframework.stereotype.Component;

import net.objecthunter.exp4j.Expression;

@Component
public class SimpleAlu implements ALU {
    @Override
    public int evaluate(String expr) {
        Expression expression = new ExpressionBuilder(expr).build();
        return (int)expression.evaluate();
    }
}
