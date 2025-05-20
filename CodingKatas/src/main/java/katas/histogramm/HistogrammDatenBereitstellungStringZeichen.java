package katas.histogramm;

import java.util.stream.Stream;

public class HistogrammDatenBereitstellungStringZeichen implements HistogrammDatenBereitstellung {
    private final String str;

    public HistogrammDatenBereitstellungStringZeichen(String str) {
        this.str = str;
    }

    @Override
    public Stream<Object> valuesStream() {
        return str.codePoints().mapToObj(Character::toString);
    }
}
