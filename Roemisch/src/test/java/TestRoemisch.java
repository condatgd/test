import berlin.gd.Roemisch;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestRoemisch {

    @ParameterizedTest
    @CsvSource({
            "1,I",
            "2,II",
            "3,III",
            "5,V",
            "4,IV",
            "6,VI",
            "7,VII",
            "8,VIII",
            "9,IX",
            "10,X",
            "14,XIV",
            "19,XIX",
            "23,XXIII",
            "34,XXXIV",
            "44,XLIV",
            "88,LXXXVIII",
            "89,LXXXIX",
            "96,XCVI",
            "256,CCLVI",
            "901,CMI",
            "1951,MCMLI"
    })
    void testZahlNachRoemisch(int num, String expected) {
        assertEquals(expected, Roemisch.toRoemisch(num));
    }
}
