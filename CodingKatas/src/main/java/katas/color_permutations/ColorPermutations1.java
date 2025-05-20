package katas.color_permutations;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ColorPermutations1 {

    public static void main(String[] args) {
        ColorPermutator colorPermutator = new ColorPermutator(4);
        System.out.println(colorPermutator.currentPerm);
        while(colorPermutator.hasNextPerm()) {
            colorPermutator.nextPerm();
            System.out.println(colorPermutator.currentPerm);
        }
    }

    private static class ColorPermutator {

        private List<Color> currentPerm;
        private Color startColor;
        private Color lastColor;

        private int numberOfPositions;
        public ColorPermutator(int n) {
            numberOfPositions = n;
            Color[] colors = Color.values();
            startColor = colors[0];
            lastColor  = colors[colors.length-1];
            currentPerm = new ArrayList<>();
            for (int i = 0; i < numberOfPositions; i++) {
                currentPerm.add(startColor);
            }
        }

        public boolean hasNextPerm() {
            return currentPerm.stream().anyMatch(c -> c!=lastColor);
        }

        public void nextPerm() {
            if(hasNextPerm()) {
                int position = 0;
                boolean carry = true;
                while (position < numberOfPositions && carry) {
                    Color colorAtPos = currentPerm.get(position);
                    Color nextColor = colorAtPos.nextColor();
                    currentPerm.set(position, nextColor);
                    if (nextColor == Color.firstColor()) {
                        position++;
                    } else {
                        carry = false;
                    }
                }
            }
        }
    }
}
