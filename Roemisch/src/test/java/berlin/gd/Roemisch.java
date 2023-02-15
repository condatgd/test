package berlin.gd;

import java.util.Arrays;
import java.util.Optional;

public class Roemisch {

    enum RomanValues {
        C(100), XC(90),
        L(50),  XL(40),
        X(10),  IX(9),
        V(5),   IV(4),
        I(1);
        private int decimalValue;
        RomanValues(int decimalValue) {
            this.decimalValue = decimalValue;
        }
        public int getDecimalValue() {
            return decimalValue;
        }
    }
    public static String toRoman(int num) {
        return Arrays.stream(RomanValues.values())
                .filter(r -> num >= r.getDecimalValue())
                .findFirst()
                .map(r -> r.name() + toRoman(num - r.getDecimalValue()))
                .orElse("");
    }

    public static int toInt(String roman) {
        return Arrays.stream(RomanValues.values())
                .filter(r -> roman.startsWith(r.name()))
                .findFirst()
                .map(r -> r.getDecimalValue() + toInt(roman.substring(r.name().length())))
                .orElse(0)
        ;
    }

}
