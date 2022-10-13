package de.condat.gd.calc;

import java.util.function.Function;
import java.util.stream.Stream;

public class Calc {

	Function<CalcState, CalcState> c = state -> state.reset();

	Function<Integer, Function<CalcState, CalcState>> curriedD = i -> state -> state.addDigit(i);

	Function<CalcState, CalcState> plus   = state -> state.setOperation("+");
	Function<CalcState, CalcState> equals = state -> state.equals();
	Function<CalcState, CalcState> nop    = state -> state;

	public CalcState calcState = new CalcState();

	public void type(String s) {
		Stream<Character> characterStream = s.chars().mapToObj(c -> (char) c);
		characterStream.forEach(c -> getFun(c).apply(calcState));
	}

	private Function<CalcState, CalcState> getFun(Character ch) {
		if (Character.isDigit(ch)) {
			return curriedD.apply(Character.getNumericValue(ch));
		} else {
			switch (ch) {
			case 'c': return c;
			case '+': return plus;
			case '-': return state -> state.setOperation("-"); // oder so ...
			case '*': return state -> state.setOperation("*");
			case '/': return state -> state.setOperation("/");
			case '=': return equals;
			default:
				return nop;
			}
		}
	}

	public void show() {
		calcState.show();
	}

}
