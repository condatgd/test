package de.condat.gd.calc1;

import java.util.function.Function;

public class CalcState1 {

	Function<String, Double> toDouble = s -> Double.valueOf(s);

	private int state = 1;
	private String n1;
	private MathOperation mathOp;
	private String n2;

	public CalcState1 clear() {
		state = 1;
		n1 = "";
		n2 = "";
		return (CalcState1) this.clone();
	}

	public CalcState1 digit(int d) {
		switch (state) {
		case 1:
			n1 += d;
			break;
		case 2:
			n2 += d;
			break;
		}
		return (CalcState1) this.clone();
	}

	public CalcState1 setMathOp(MathOperation mathOp) {
		this.mathOp = mathOp;
		state = 2;
		return (CalcState1) this.clone();
	}

	public CalcState1 eq() {
		n1 = mathOp.getResult(toDouble.apply(n1), toDouble.apply(n2)).toString();
		state = 1;
		n2 = "";
		return (CalcState1) this.clone();

	}

	public void show() {
		System.out.println("this: " + this);
		System.out.println("state: " + state);
		System.out.println("n1   : " + n1);
		System.out.println("mathOp: " + mathOp);
		System.out.println("n2   : " + n2);
		System.out.println();
	}

	public CalcState1 clone() {
		CalcState1 s = new CalcState1();
		s.n1 = n1;
		s.n2 = n2;
		s.mathOp = mathOp;
		s.state = state;
		return s;
	}

}
