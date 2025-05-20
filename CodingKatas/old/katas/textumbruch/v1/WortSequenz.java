package katas.textumbruch.v1;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class WortSequenz {

    private final ArrayDeque<String> woerter;

    public WortSequenz(String text, int zeilenLaenge) {
        if (text == null) {
            throw new IllegalArgumentException("Text cannot be null");
        }
        this.woerter = new ArrayDeque<>();
        text = text.trim();
        if(!text.isEmpty()) {
            schreibeWoerterAufDenStack(text, zeilenLaenge);
        }
    }

    private void schreibeWoerterAufDenStack(String text, int zeilenLaenge) {
        String[] split = text.split("\\s+");
        Arrays.stream(split).forEach(wort -> unterteileWortWennNoetig(zeilenLaenge, wort));
    }

    private void unterteileWortWennNoetig(int zeilenLaenge, String wort) {
        while(wort.length()> zeilenLaenge) {
            woerter.add(wort.substring(0, zeilenLaenge));
            wort = wort.substring(zeilenLaenge);
        }
        woerter.add(wort);
    }

    public boolean wortVerfuegbar() {
        return !woerter.isEmpty();
    }

    public String naechstesWort() {
        if (!wortVerfuegbar()) {
            throw new NoSuchElementException("No more elements in the queue");
        }
        return woerter.pop();
    }
}
