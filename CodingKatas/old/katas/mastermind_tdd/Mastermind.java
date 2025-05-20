package katas.mastermind_tdd;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Mastermind {

    public static List<Integer> evaluate(List<Color> secret, List<Color> guess) throws Exception {
        if(secret==null) {
            throw new Exception("secret is null");
        }
        if(guess==null) {
            throw new Exception("guess is null");
        }
        if(secret.size()!=guess.size()) {
            throw new Exception("different length");
        }
        int c1 = 0;
        List<Color> secretReduced = new ArrayList<>(secret);
        List<Color> guessReduced = new ArrayList<>(guess);
        for(int i=0; i<guess.size(); i++) {
            Color g = guess.get(i);
            Color s = secret.get(i);
            if(g==s) {
                c1++;
                secretReduced.set(i,null);
                guessReduced.set(i,null);
            }
            System.out.println("secretReduced: " +secretReduced);
            System.out.println("guessReduced: " +guessReduced);
        }
        int c2 = 0;
        for(int i=0; i<guessReduced.size(); i++) {
            Color g = guessReduced.get(i);
            if(g!=null && secretReduced.contains(g)) {
                c2++;
            }
        }

        return List.of(c1,c2);
    }
}
