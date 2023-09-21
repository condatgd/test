package katas.taschenrechner.impl;

import katas.taschenrechner.Code;
import lombok.Builder;
import lombok.Data;

import java.util.Stack;

public class PrecedenceALU extends SimpleALU {

    @Data
    @Builder
    static class NumberOrOp {
        int number;
        Code op;
    }

    Stack<NumberOrOp> stack;

    public PrecedenceALU() {
        stack = new Stack<>();
    }

    @Override
    public void number(int number) {
        stack.push(NumberOrOp.builder().number(number).build());
    }

    @Override
    public void operation(Code op) {
        stack.push(NumberOrOp.builder().op(op).build());
    }

    @Override
    public boolean opPrefered(Code newOp) {
        if(stack.size()>2) {
            NumberOrOp arg2 = stack.pop();
            NumberOrOp op = stack.pop();
            NumberOrOp arg1 = stack.pop();
            stack.push(arg1);
            stack.push(op);
            stack.push(arg2);
            return op.getOp()!=null && newOp.getPrecedence() > op.getOp().getPrecedence();
        }
        return false;
    }
    @Override
    public boolean calculationPossible() {
        if(hasPower() && stack.size()>2) {
            NumberOrOp arg2 = stack.pop();
            NumberOrOp op = stack.pop();
            NumberOrOp arg1 = stack.pop();
            stack.push(arg1);
            stack.push(op);
            stack.push(arg2);
            return op.getOp()!=null;
        }
        return false;
    }

    @Override
    public int calculate() {
        int result = 0;
        if(calculationPossible()) {
            int arg2 = stack.pop().getNumber();
            Code op = stack.pop().getOp();
            int arg1 = stack.pop().getNumber();
            switch (op) {
                case PLUS -> result = arg1 + arg2;
                case MULT -> result = arg1 * arg2;
            }
        }
        return result;
    }

    @Override
    public void reset() {
        stack = new Stack<>();
    }
}
