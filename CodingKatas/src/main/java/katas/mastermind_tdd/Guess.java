package katas.mastermind_tdd;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class Guess {
    private List<Color> guessColors;

    public Guess(String guessStr) {
        guessColors = Color.strToColorList(guessStr);
    }

    public int size() {
        return guessColors.size();
    }

    public Color getColorAtPosition(int i) {
        return guessColors.get(i);
    }
}
