package katas.histogramm;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class HistogrammStringTest {

    @Test
    void test_leereImplementierung() {
        HistogrammDatenBereitstellung histogrammDatenBereitstellung = new HistogrammDatenBereitstellungStringZeichen("");
        Histogramm h = new HistogrammErzeugung(histogrammDatenBereitstellung);
        assertThat(h.numberOfValues(), is(0));
    }

    @Test
    void test_einZeichen() {
        HistogrammDatenBereitstellung histogrammDatenBereitstellung = new HistogrammDatenBereitstellungStringZeichen("a");
        Histogramm h = new HistogrammErzeugung(histogrammDatenBereitstellung);
        assertThat(h.numberOfValues(), is(1));
    }

    @Test
    void test_mehrereGleicheZeichen() {
        HistogrammDatenBereitstellung histogrammDatenBereitstellung = new HistogrammDatenBereitstellungStringZeichen("aaaaa");
        Histogramm h = new HistogrammErzeugung(histogrammDatenBereitstellung);
        assertThat(h.numberOfValues(), is(1));
        assertThat(h.countOfValue("a"), is(5));
    }

    @Test
    void test_mehrereVerschiedeneZeichen() {
        HistogrammDatenBereitstellung histogrammDatenBereitstellung = new HistogrammDatenBereitstellungStringZeichen("ababc");
        Histogramm h = new HistogrammErzeugung(histogrammDatenBereitstellung);
        assertThat(h.numberOfValues(), is(3));
        assertThat(h.countOfValue("a"), is(2));
        assertThat(h.countOfValue("b"), is(2));
        assertThat(h.countOfValue("c"), is(1));
    }

    @Test
    void test_mehrereSonderZeichen() {
        HistogrammDatenBereitstellung histogrammDatenBereitstellung = new HistogrammDatenBereitstellungStringZeichen("💕😊💕A");
        Histogramm h = new HistogrammErzeugung(histogrammDatenBereitstellung);
        assertThat(h.numberOfValues(), is(3));
        assertThat(h.countOfValue("😊"), is(1));
        assertThat(h.countOfValue("💕"), is(2));
        assertThat(h.countOfValue("A"), is(1));
    }

}
