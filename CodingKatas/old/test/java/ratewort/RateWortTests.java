package ratewort;

import katas.calculator.Calculator;
import katas.ratewort.RateWort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RateWortTests {

    @Test
    void test() {
        RateWort rw = new RateWort("Weihnachten");
        rw.rateBuchstabe('W');
        rw.rateBuchstabe('a');
        rw.rateBuchstabe('n');
        rw.rateBuchstabe('e');
        rw.rateBuchstabe('c');
        rw.rateBuchstabe('h');
        rw.rateBuchstabe('i');
        rw.rateBuchstabe('t');
    }


}
