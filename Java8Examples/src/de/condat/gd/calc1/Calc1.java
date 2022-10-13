package de.condat.gd.calc1;

import java.util.Arrays;
import java.util.stream.Stream;

public class Calc1 {

	private CalcState1 calcState = new CalcState1();

	public void type(String s) {
		Stream<Character> characterStream = s.chars().mapToObj(c -> (char) c);

		// mit Seiteneffekt
		// characterStream.forEach(c -> getCalcFunction(c).getResult(calcState));

		// ohne Seiteneffekte
		calcState = characterStream.reduce(calcState, (state, c) -> getCalcFunction(c).getResult(state),
				(s1, s2) -> s2);
	}

	private CalcOperation getCalcFunction(Character c) {
		return Arrays.asList(CalcOperation.values()).stream().filter(cOp -> cOp.getSymbol().equals("" + c)).findFirst()
				.get();
	}

	public void show() {
		calcState.show();
	}
}
