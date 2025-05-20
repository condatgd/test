package mastermind_tdd;

import katas.mastermind_tdd.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class MastermindTests {

    @Test
    void evaluateEmpty() throws Exception {
        List<Color> mtList = Color.strToColorList("");
        Secret secret = new Secret(mtList);
        Guess guess   = new Guess(mtList);
        Hints hints = secret.computeHints(guess);
        assertEquals(new Hints(0,0), hints);
    }

    @Test
    void evaluateGuessNull() {
        List<Color> mtList = Color.strToColorList("");
        Secret secret = new Secret(mtList);
        Guess guess   = null;
        Exception exception = assertThrows(Exception.class,
                () -> secret.computeHints(guess)
        );
        assertEquals("guess is null", exception.getMessage());
    }

    @Test
    void evaluateDifferentSizes() {
        Secret secret = new Secret(Color.strToColorList("r"));
        Guess guess   = new Guess(Color.strToColorList("bp"));
        Exception exception = assertThrows(Exception.class,
                () -> secret.computeHints(guess)
        );
        assertEquals("different length", exception.getMessage());
    }

    @ParameterizedTest
    @CsvSource(value = {
            "rp, ru, 1, 0", // evaluateOneMatchFirst
            "pr, ur, 1, 0", // evaluateOneMatchSecond
            "pr, rp, 0, 2", // evaluateTwoColorsCorrect
            "prr, rrp, 1, 2", // evaluateOneMatchAndTwoColorsCorrect
            "prb, prb, 3, 0", // evaluateAllMatch
            "bprb, pbbr, 0, 4", // evaluateAllThereButNoMatch
            "bprb, brpb, 2, 2", // evaluate2Match2Correct
            "bprb, bbbb, 2, 0", // evaluateGuessAllSameColor
            "bpru, bpru, 4, 0", // evaluateWin
            "bbbb, bbbb, 4, 0", // evaluateWinAllSameColor
            "bbbb, bprb, 2, 0", // evaluateGuessAllSameColor
    })
    void evalTest(Secret secret, Guess guess, int blackExpected, int whiteExpected) throws Exception {
        Hints hints = secret.computeHints(guess);
        assertEquals(new Hints(blackExpected,whiteExpected), hints);
    }

}
