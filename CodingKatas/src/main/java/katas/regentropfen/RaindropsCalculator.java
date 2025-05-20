package katas.regentropfen;

import java.util.List;
import java.util.stream.IntStream;

public class RaindropsCalculator {

    public int calculateRainDropCount(List<Integer> topologie) {
        return IntStream.range(1, topologie.size()-1)                    // Am Anfang oder Ende brauchen wir gar nicht anzufangen.
                .map(index -> dropsOfWaterKept(topologie, index)).sum(); // Summe aller Tropfen
    }

    private Integer dropsOfWaterKept(List<Integer> topologie, Integer index) {
        int i = 0;
        while (dropOfWaterKept(topologie, index, i)) i++; // auffüllen, bis es "überläuft"
        return i;
    }

    public boolean dropOfWaterKept(List<Integer> topologie, Integer index, int dropCount) {
        Integer topH = topologie.get(index);
        Integer newHeight = topH + dropCount + 1;                                       // aktuelle Höhe am index plus Wasserhöhe plus 1 für Test ob das überläuft
        List<Integer> leftHeights  = topologie.subList(0, index);                       // linke Liste für Begrenzungssuche
        List<Integer> rightHeights = topologie.subList(index + 1, topologie.size());    // rechte Liste für Begrenzungssuche
        boolean hasLeftBoundary  = hasBoundary(leftHeights, newHeight);                 // Prüfung boundary links
        boolean hasRightBoundary = hasBoundary(rightHeights, newHeight);                // Prüfung boundary rechts
        return hasLeftBoundary && hasRightBoundary;                                     // Beide Seiten begrenzt?
    }

    private static boolean hasBoundary(List<Integer> sublist, Integer newHeight) {
        return sublist.stream().anyMatch(someBondaryHeight -> someBondaryHeight >= newHeight); // Die Reihenfolge spielt keine Rolle
    }

}
