package mastermind;

import group.msg.jpowermonitor.dto.SensorValue;
import group.msg.jpowermonitor.dto.SensorValues;
import group.msg.jpowermonitor.junit.JPowerMonitorExtension;
import katas.mastermind.Mastermind;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;
import java.util.List;

@ExtendWith({JPowerMonitorExtension.class})
public class MastermindTests {

    @SensorValues
    public List<SensorValue> sensorValues = new ArrayList<>();
    @RepeatedTest(10)
    void sucess() {

        for(int i=0; i<100000; i++) {
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
    @AfterEach
    void afterEach() {
        System.out.println("sensorValues: " + sensorValues);
    }
}
