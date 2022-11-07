package de.berlin.gd;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Mastermind {

    public static final int ROW_LENGTH = 4;

    public static void main(String[] args) {
        SecretHolder secretHolder = new SecretHolder(ROW_LENGTH);
        Player player = new Player();
        List<Guess> guessesHistory = new ArrayList<>();
        Guess guess;
        do {
            guess = player.findNewGuess(guessesHistory);
            secretHolder.evaluate(guess); // the secret is not visible for the Player !!!
            System.out.println("guess: " + guess);
            guessesHistory.add(guess);
        } while (!guess.isSolution());

    }

    public static class SecretHolder {
        Row secret;

        public SecretHolder(int rowLength) {
            secret = new Row(rowLength);
            System.out.println("SecretHolder secret: " + secret);
        }

        public void evaluate(Guess guess) {
            guess.evaluate(secret);
        }
    }

    public static class Player {
        public Guess findNewGuess(List<Guess> guessesHistory) {
            Row currentRow;
            if (guessesHistory.isEmpty()) {
                currentRow = new Row(Color.firstColor(), ROW_LENGTH);
            } else {
                currentRow = guessesHistory.get(guessesHistory.size() - 1).getRow();
            }
            while (currentRow.contradicts(guessesHistory)) {
                currentRow = nextRow(currentRow);
            }
            return new Guess(currentRow, 0, 0);
        }

        private Row nextRow(Row currentRow) {
            Row newRow = new Row(currentRow);
            int currentRowLength = currentRow.getLength();
            for (int i = 0; i < currentRowLength; i++) {
                Color colorI = currentRow.getColor(i);
                Color nextColor = colorI.nextColor();
                newRow.setColor(i, nextColor);
                if (nextColor != Color.firstColor())
                    break;
            }
            return newRow;
        }
    }

    @Data
    @AllArgsConstructor
    public static class Guess {
        Row row;
        int white;
        int black;

        public void evaluate(Row secret) {
            this.black = row.evaluateBlack(secret);
            this.white = row.evaluateWhite(secret);
        }

        public boolean isSolution() {
            return black == ROW_LENGTH;
        }
    }

    @Data
    public static class Row {
        Random rand = new Random();
        List<Color> colors;

        public Row(int length) {
            colors = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                int randomColorcode = rand.nextInt(Color.numColors());
                colors.add(Color.values()[randomColorcode]);
            }
        }

        public Row(Color color, int width) {
            colors = new ArrayList<>();
            for (int i = 0; i < width; i++) {
                colors.add(color);
            }
        }

        public Row(Row row) {
            this.rand = new Random();
            this.colors = new ArrayList<>();
            this.colors.addAll(row.getColors());
        }

        public int getLength() {
            return colors.size();
        }

        public Color getColor(int i) {
            return colors.get(i);
        }

        public String toString() {
            return colors.toString();
        }

        public boolean contradicts(List<Guess> guessesHistory) {
            Row given_secret = this;
            return guessesHistory.stream().anyMatch(
                    gh -> gh.getBlack() != gh.getRow().evaluateBlack(given_secret) ||
                          gh.getWhite() != gh.getRow().evaluateWhite(given_secret)
            );
        }

        public int evaluateBlack(Row secret) {
            return (int)IntStream.range(0, secret.getColors().size())
                    .filter(i -> secret.getColor(i) == this.getColor(i))
                    .count();
        }

        public int evaluateWhite(Row secret) {
            return (int)IntStream.range(0, secret.getColors().size())
                    .filter(i -> secret.getColor(i) != this.getColor(i) &&
                                 secret.getColors().contains(this.getColor(i)))
                    .count();
        }

        public void setColor(int i, Color nextColor) {
            colors.set(i, nextColor);
        }
    }

    private enum Color {
        red, green, orange, yellow, blue, white;

        public Color nextColor() {
            int nextIndex = this.ordinal() + 1;
            if (Color.values().length <= nextIndex)
                return firstColor();
            else
                return Color.values()[nextIndex];
        }

        public static Color firstColor() {
            return red;
        }

        public static int numColors() {
            return Color.values().length;
        }
    }
}
