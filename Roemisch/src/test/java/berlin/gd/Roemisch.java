package berlin.gd;

import java.util.Arrays;

public class Roemisch {

    enum RoemischEnum {
        X(10), IX(9),
        V(5),  IV(4),
        I(1)
        ;

        private int i;

        RoemischEnum(int i) {
            this.i = i;
        }

        public int getI() {
            return i;
        }
    }
    public static String toRoemisch(int num) {
        return Arrays.stream(RoemischEnum.values())
                .filter(r -> num >= r.getI())
                .findFirst()
                .map(r -> r.name() + toRoemisch(num - r.getI()))
                .orElse("");
    }
}
