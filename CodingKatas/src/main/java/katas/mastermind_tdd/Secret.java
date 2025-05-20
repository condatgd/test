package katas.mastermind_tdd;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class Secret {
    private List<Color> secretColors;

    public Secret(String secretStr) {
        secretColors = Color.strToColorList(secretStr);
    }

    public int size() {
        return secretColors.size();
    }

    public Hints computeHints(Guess guess) throws Exception {
        if(guess==null) {
            throw new Exception("guess is null");
        }
        if(size()!=guess.size()) {
            throw new Exception("different length");
        }

        int blackCount = 0;
        List<Color> secretReduced = new ArrayList<>(secretColors);
        List<Color> guessReduced = new ArrayList<>(guess.getGuessColors());
        for(int i=0; i<guess.size(); i++) {
            if(guess.getColorAtPosition(i).equals(getSecretColorAtPosition(i))) {
                blackCount++;
                secretReduced.set(i, Color._blank);
                guessReduced.set(i, Color._blank);
            }
        }
        int whiteCount = 0;
        for(int i=0; i<guessReduced.size(); i++) {
            Color guess_i = guessReduced.get(i);
            if(guess_i!=Color._blank && secretReduced.contains(guess_i)) {
                whiteCount++;
            }
        }
        return new Hints(blackCount, whiteCount);
    }
    private Color getSecretColorAtPosition(int i) {
        return secretColors.get(i);
    }

}
