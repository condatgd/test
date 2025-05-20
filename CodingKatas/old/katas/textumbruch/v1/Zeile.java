package katas.textumbruch.v1;

import java.util.ArrayList;
import java.util.List;

public class Zeile {
    private final List<String> woerter;
    private final int maximaleZeilenLaenge;
    private int wortLaengenSumme;

    public Zeile(int maximaleZeilenLaenge) {
        this.woerter = new ArrayList<>();
        this.maximaleZeilenLaenge = maximaleZeilenLaenge;
    }

    public boolean versucheWortHinzuzufuegen(String wort) {
        if(wort.length() <= platzVerfuegbar(0)) {
            woerter.add(wort);
            wortLaengenSumme += wort.length();
            return true;
        }
        return false;
    }

    public String getInhalt(boolean blocksatz) {
        if (blocksatz && woerter.size() > 1) {
            return blocksatz();
        }
        return String.join(" ", woerter);
    }
    private String blocksatz() {
        int restPlatz = platzVerfuegbar(1);
        int anzahlTrenner = woerter.size()-1;
        StringBuilder stringBuilder = new StringBuilder();
        int trennerZusatz = (restPlatz / anzahlTrenner) + 1;
        int trennerRest = restPlatz % anzahlTrenner;
        String stdSpacingStr = " ".repeat(trennerZusatz);
        for(int i=0; i<woerter.size() - 1; i++) {
            stringBuilder.append(woerter.get(i));
            stringBuilder.append(stdSpacingStr);
            boolean nochSpaceZuVergeben = trennerRest > 0;
            if(nochSpaceZuVergeben) {
                stringBuilder.append(" ");
                trennerRest--;
            }
        }
        stringBuilder.append(woerter.get(woerter.size()-1));
        return stringBuilder.toString();
    }

    int platzVerfuegbar(int offset) {
        if (offset < 0) {
            throw new IllegalArgumentException("offset cannot be negative");
        }
        int platz = maximaleZeilenLaenge - wortLaengenSumme - woerter.size() + offset;
        return Math.max(platz, 0);
    }
}
