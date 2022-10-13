package berlin.gd.archive;

import java.util.Arrays;

public class Roemisch {
    enum RoemischeZahl {

        M(1000), CM(900),
        D(500), CD(400),
        C(100), XC(90),
        L(50),  XL(40),
        X(10),  IX(9),
        V(5),   IV(4),
        I(1);

        private final int n;
        RoemischeZahl(int n) {
            this.n = n;
        }

        int getValue() {
            return n;
        }
    }
    public static String toRoemisch(int n) {
        return Arrays.stream(RoemischeZahl.values())
                .filter(roemischeZahl -> roemischeZahl.n <= n)
                .findFirst()
                .map(roemischeZahl ->
                        roemischeZahl + toRoemisch(n - (roemischeZahl.getValue()))
                )
                .orElse("");
    }

}
