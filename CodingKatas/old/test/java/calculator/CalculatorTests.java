package calculator;

import katas.calculator.Calculator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTests {

    @ParameterizedTest
    @CsvSource({
            "1",
            "2",
            "4711"
    })
    void enterNumber(int a) {
        Calculator calc = new Calculator();
        calc.enterNumber(a);
        assertEquals(a, calc.getDisplayedNumber());
    }

    @ParameterizedTest
    @CsvSource({
            "1",
            "2",
            "4711"
    })
    void pressPlus(int a) {
        Calculator calc = new Calculator();
        calc.enterNumber(a);
        calc.pressPlus();
        assertEquals("enter a second number", calc.getState());
    }


    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "12,13,25"
    })
    void add2(int a, int b, int result) {
        int expectedResult = a + b;
        Calculator calc = new Calculator();
        calc.enterNumber(a);
        calc.pressPlus();
        calc.enterNumber(b);
        calc.pressEquals();
        assertEquals(expectedResult, calc.getDisplayedNumber());
    }

    @ParameterizedTest
    @CsvSource({
            "1,1,1,3",
            "12,13,5,30"
    })
    void add3(int a, int b, int c,int result) {
        int expectedResult = a + b + c;
        Calculator calc = new Calculator();
        calc.enterNumber(a);
        calc.pressPlus();
        calc.enterNumber(b);
        calc.pressPlus();
        calc.enterNumber(c);
        calc.pressEquals();
        assertEquals(expectedResult, calc.getDisplayedNumber());
    }

    @ParameterizedTest
    @CsvSource({
            "1,1,3",
            "12,13,38"
    })
    void doubleEquals(int a, int b, int result) {
        int expectedResult = a + (2*b);
        Calculator calc = new Calculator();
        calc.enterNumber(a);
        calc.pressPlus();
        calc.enterNumber(b);
        calc.pressEquals();
        calc.pressEquals();
        assertEquals(expectedResult, calc.getDisplayedNumber());
    }


}
