package de.condat.gd.calc;

import java.math.BigInteger;
import java.util.function.BiFunction;
import java.util.function.Function;

public class CalcState {

	Function<String, BigInteger> toBigInteger = s -> BigInteger.valueOf(Integer.parseInt(s));

	private int state;
	private String n1;
	private String n2;
	BiFunction<BigInteger, BigInteger, BigInteger> opFun;

	public CalcState setOperation(String op) {
		switch (op) {
		case "+":
			opFun = BigInteger::add;
			break;
		case "-":
			opFun = BigInteger::subtract;
			break;
		case "*":
			opFun = BigInteger::multiply;
			break;
		case "/":
			opFun = BigInteger::divide;
			break;
		}
		state = 2;
		n2 = "";
		return this;
	}

	public CalcState addDigit(int i) {
		switch (state) {
		case 1:
			n1 += i;
			break;
		case 2:
			n2 += i;
			break;
		}
		return this;
	}

	public CalcState equals() {
		n1 = opFun.apply(toBigInteger.apply(n1), toBigInteger.apply(n2)).toString();
		state = 1;
		return this;
	}

	public CalcState reset() {
		state = 1;
		n1 = "";
		n2 = "";
		return this;
	}

	public void show() {
		System.out.println("state: " + state);
		System.out.println("n1   : " + n1);
		System.out.println("opFun: " + opFun);
		System.out.println("n2   : " + n2);
	}

}
