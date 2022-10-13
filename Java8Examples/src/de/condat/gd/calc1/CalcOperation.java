package de.condat.gd.calc1;

public enum CalcOperation {

    C("c",  state -> state.clear()),
	c0("0", state -> state.digit(0)),
	c1("1", state -> state.digit(1)),
	c2("2", state -> state.digit(2)),
	c3("3", state -> state.digit(3)),
	c4("4", state -> state.digit(4)),
	c5("5", state -> state.digit(5)),
	c6("6", state -> state.digit(6)),
	c7("7", state -> state.digit(7)),
	c8("8", state -> state.digit(8)),
	c9("9", state -> state.digit(9)),
	PLUS("+",  state -> state.setMathOp(MathOperation.ADD)),
	MINUS("-", state -> state.setMathOp(MathOperation.SUB)),
	MULT("*",  state -> state.setMathOp(MathOperation.MUL)),
	DIV("/",   state -> state.setMathOp(MathOperation.DIV)),
	EQ("=", state -> state.eq());
	
	private final String symbol;
	private final CalcOperator calcOperator;

	private CalcOperation(String symbol, CalcOperator operator) {
		this.symbol = symbol;
		this.calcOperator = operator;
	}

	public CalcState1 getResult(CalcState1 cState) {
		CalcState1 cStateNew = calcOperator.apply(cState);
		return cStateNew;
	}

	public String getSymbol() {
		return symbol;
	}

}
