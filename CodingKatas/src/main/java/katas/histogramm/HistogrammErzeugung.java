package katas.histogramm;

import java.util.HashMap;
import java.util.Map;

public class HistogrammErzeugung implements Histogramm {

    private final HistogrammDatenBereitstellung histogrammDatenBereitstellung;

    private final Map<Object, Integer> histogramm;

    public HistogrammErzeugung(HistogrammDatenBereitstellung histogrammDatenBereitstellung) {
        this.histogrammDatenBereitstellung = histogrammDatenBereitstellung;
        histogramm = new HashMap<>();
        histogrammDatenBereitstellung.valuesStream().forEach(value -> {
            histogramm.merge(value, 1, Integer::sum);
        });
    }

    @Override
    public Integer numberOfValues() {
        return histogramm.keySet().size();
    }

    @Override
    public Integer countOfValue(Object value) {
        return histogramm.getOrDefault(value, 0);
    }
}
