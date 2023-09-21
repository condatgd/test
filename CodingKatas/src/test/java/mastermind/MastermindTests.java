package mastermind;

import katas.gameoflife.GameOfLife;
import katas.mastermind.Mastermind;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MastermindTests {

    @Test
    void sucess() {
        Mastermind.SecretHolder secretHolder = new Mastermind.SecretHolder(Mastermind.ROW_LENGTH);
        Mastermind.Player player = new Mastermind.Player();
        List<Mastermind.Guess> guessesHistory = new ArrayList<>();
        Mastermind.Guess guess;
        do {
            guess = player.findNewGuess(guessesHistory);
            secretHolder.evaluate(guess); // the secret is not visible for the Player !!!
            System.out.println("guess: " + guess);
            guessesHistory.add(guess);
        } while (!guess.isSolution());
    }

}
