package de.berlin.gd.calculator.domain.model;

import lombok.Getter;

public enum Code {
    N0('0',false, 0),
    N1('1', false, 0),
    N2('2', false, 0),
    N3('3', false, 0),
    N4('4', false, 0),
    N5('5', false, 0),
    N6('6', false, 0),
    N7('7', false, 0),
    N8('8', false, 0),
    N9('9', false, 0),
    PLUS('+', true, 1),
    MULT('*', true, 2),
    EQ('=', true, 100);

    private final char c;
    private final boolean isOp;
    @Getter
    private final int precedence;

    Code(char c, boolean isOp, int precedence) {
        this.c = c;
        this.isOp = isOp;
        this.precedence = precedence;
    }

    public int getDigit() {
        return Integer.parseInt("" + c);
    }

    public boolean isOp() {
        return isOp;
    }

    public char getChar() { return c; }
}
