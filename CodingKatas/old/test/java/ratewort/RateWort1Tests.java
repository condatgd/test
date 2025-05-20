package ratewort;

import katas.ratewort.RateWort1;
import org.junit.jupiter.api.Test;

public class RateWort1Tests {

    @Test
    void test() {
        RateWort1 rw = new RateWort1("Weihnachten");
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
