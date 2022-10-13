package de.condat.gd.calc1;

import java.util.function.DoubleBinaryOperator;

public enum MathOperation {

	ADD("+", (d1, d2) -> d1 + d2), // or Double::sum
	SUB("-", (d1, d2) -> d1 - d2), 
	MUL("*", (d1, d2) -> d1 * d2), 
	DIV("/", (d1, d2) -> d1 / d2);

	private final String symbol;
	private final DoubleBinaryOperator operator;

	private MathOperation(String symbol, DoubleBinaryOperator operator) {
		this.symbol = symbol;
		this.operator = operator;
	}

	public Double getResult(Double d1, Double d2) {
		return operator.applyAsDouble(d1, d2);
	}

	public String getSymbol() {
		return symbol;
	}

}