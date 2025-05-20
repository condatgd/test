package mastermind_tdd;

import katas.mastermind_tdd.Color;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class MastermindTests {

    @Test
    void evaluateEmpty() throws Exception {
        List<Integer> result = Mastermind.evaluate(List.of(), List.of());
        assertEquals(List.of(0,0), result );
    }

    @Test
    void evaluateSecretNull() {
        Exception exception = assertThrows(Exception.class,
                () -> Mastermind.evaluate(null, List.of(Color.blue, Color.purple))
        );
        assertEquals("secret is null", exception.getMessage());
    }

    @Test
    void evaluateGuessNull() {
        Exception exception = assertThrows(Exception.class,
                () -> Mastermind.evaluate(List.of(Color.blue, Color.purple), null)
        );
        assertEquals("guess is null", exception.getMessage());
    }


    @Test
    void evaluateDifferentSizes() {
        Exception exception = assertThrows(Exception.class,
                () -> Mastermind.evaluate(List.of(Color.red), List.of(Color.blue, Color.purple))
        );
        assertEquals("different length", exception.getMessage());
    }

    @Test
    void evaluateOneMatchFirst() throws Exception {
        List<Integer> result = Mastermind.evaluate(List.of(Color.red, Color.pink), List.of(Color.red, Color.purple));
        assertEquals(List.of(1,0), result );
    }

    @Test
    void evaluateOneMatchSecond() throws Exception {
        List<Integer> result = Mastermind.evaluate(List.of(Color.pink, Color.red), List.of(Color.purple, Color.red));
        assertEquals(List.of(1,0), result );
    }

    @Test
    void evaluateTwoColorsCorrect() throws Exception {
        List<Integer> result = Mastermind.evaluate(List.of(Color.pink, Color.red), List.of(Color.red, Color.pink));
        assertEquals(List.of(0,2), result );
    }

    @Test
    void evaluateOneMatchAndTwoColorsCorrect() throws Exception {
        List<Integer> result = Mastermind.evaluate(List.of(Color.pink, Color.red, Color.red), List.of(Color.red, Color.red, Color.pink));
        assertEquals(List.of(1,2), result );
    }

    @Test
    void evaluateAllMatch() throws Exception {
        List<Integer> result = Mastermind.evaluate(List.of(Color.pink, Color.red, Color.blue), List.of(Color.pink, Color.red, Color.blue));
        assertEquals(List.of(3,0), result );
    }

}
