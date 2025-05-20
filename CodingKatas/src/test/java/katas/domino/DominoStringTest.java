package katas.domino;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DominoStringTest {

    @ParameterizedTest
    @CsvSource(value = {
            ", false",
            "1, true",
            "1_1, false",
            "2_1, true",
            "2__1, false",
            "3__1, true",
            "__3__1__, true",
            "__3___1__, false",
            "4___2_2_2, true",
            "4___2_5____2, true",
            "7_2__2, true",
    })
    void dominoTest(String dominoString, boolean faellt) {
        assertEquals(faellt, DominoString.allDominosWillFallKerstin(dominoString));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "7_2__2_2_2, true",
            "7_2__2_1_2, false",
    })
    void dominoTestKerstin(String dominoString, boolean faellt) {
        assertEquals(faellt, DominoString.allDominosWillFallKerstin(dominoString));
    }

}
