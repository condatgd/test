package katas.histogramm;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class HistogrammDatenBereitstellungFarben implements HistogrammDatenBereitstellung {
    private final List<Farbe> farben;

    public HistogrammDatenBereitstellungFarben(List<Farbe> farben) {
        this.farben = farben;
    }

    @Override
    public Stream<Object> valuesStream() {
        return Arrays.stream(farben.toArray());
    }
}
