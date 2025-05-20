package katas.histogramm;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class HistogrammFarbenTest {


    @Test
    void test_mehrereGleicheZeichen() {
        HistogrammDatenBereitstellung histogrammDatenBereitstellung =
                new HistogrammDatenBereitstellungFarben(List.of(Farbe.rot, Farbe.blau, Farbe.rot, Farbe.gelb));
        Histogramm h = new HistogrammErzeugung(histogrammDatenBereitstellung);
        assertThat(h.numberOfValues(), is(3));
        assertThat(h.countOfValue(Farbe.rot), is(2));
        assertThat(h.countOfValue(Farbe.blau), is(1));
        assertThat(h.countOfValue(Farbe.gruen), is(0));
    }


}
