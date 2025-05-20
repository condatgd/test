package katas.ratewort;

import java.util.List;
import java.util.stream.Collectors;

public class RateWort {
    private final Wort wort;

    public RateWort(String wortStr) {
        this.wort = new Wort(wortStr);
    }

    public void rateBuchstabe(char c) {
        wort.pruefe(c);
        System.out.println(c + ": " + wort.text() + " | " + wort.geloest() );
    }

    private static class Wort {
        private final List<Buchstabe> wortBuchstaben;

        public Wort(String wortStr) {
            this.wortBuchstaben = wortStr.chars().mapToObj(Buchstabe::new).collect(Collectors.toList());
        }

        public void pruefe(char c) {
            wortBuchstaben.forEach(buchstabe -> buchstabe.setzeGetroffenBoolean(c));
        }

        public boolean geloest() {
            return wortBuchstaben.stream().allMatch(buchstabe -> buchstabe.getroffen);
        }

        public String text() {
            return wortBuchstaben.stream().map(Buchstabe::toString).collect(Collectors.joining());
        }
    }
    private static class Buchstabe {
        private final char c;
        private boolean getroffen;
        public Buchstabe(int c) {
            this.c = (char)c;
            this.getroffen = false;
        }

        public void setzeGetroffenBoolean(char c) {
            getroffen = getroffen || this.c==c;
        }

        public String toString() {
            return getroffen ? ""+c : "_";
        }

    }
}
