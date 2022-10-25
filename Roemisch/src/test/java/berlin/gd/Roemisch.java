package berlin.gd;

import java.util.Arrays;

public class Roemisch {

    enum RomanValues {
        X(10), IX(9), V(5),  IV(4), I(1);
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
}
