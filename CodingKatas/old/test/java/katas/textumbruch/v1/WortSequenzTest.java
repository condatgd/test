package katas.textumbruch.v1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

public class WortSequenzTest {

    @Test
    public void wortSequenzCorrectInitialization() {
        String inputText = "Hallo Welt!";
        int zeilenLaenge = 5;
        WortSequenz wortSequenz = new WortSequenz(inputText, zeilenLaenge);

        Assertions.assertTrue(wortSequenz.wortVerfuegbar());
    }

    @Test
    public void wortSequenzCorrectBehaviourWithLongWord() {
        String inputText = "Internationale";
        int zeilenLaenge = 5;
        WortSequenz wortSequenz = new WortSequenz(inputText, zeilenLaenge);

        Assertions.assertEquals("Inter", wortSequenz.naechstesWort());
        Assertions.assertEquals("natio", wortSequenz.naechstesWort());
        Assertions.assertEquals("nale", wortSequenz.naechstesWort());
        Assertions.assertFalse(wortSequenz.wortVerfuegbar());
    }

    @Test
    public void wortSequenzCorrectBehaviourWithMultipleWords() {
        String inputText = "Lorem ipsum dolor sit amet";
        int zeilenLaenge = 4;
        WortSequenz wortSequenz = new WortSequenz(inputText, zeilenLaenge);

        Assertions.assertEquals("Lore", wortSequenz.naechstesWort());
        Assertions.assertEquals("m", wortSequenz.naechstesWort());
        Assertions.assertEquals("ipsu", wortSequenz.naechstesWort());
        Assertions.assertEquals("m", wortSequenz.naechstesWort());
        Assertions.assertEquals("dolo", wortSequenz.naechstesWort());
        Assertions.assertEquals("r", wortSequenz.naechstesWort());
        Assertions.assertEquals("sit", wortSequenz.naechstesWort());
        Assertions.assertEquals("amet", wortSequenz.naechstesWort());
        Assertions.assertFalse(wortSequenz.wortVerfuegbar());
    }

    @Test
    public void wortSequenzThrowsErrorWhenEmpty() {
        String inputText = "Hello";
        int zeilenLaenge = 5;
        WortSequenz wortSequenz = new WortSequenz(inputText, zeilenLaenge);

        Assertions.assertEquals("Hello", wortSequenz.naechstesWort());
        Assertions.assertThrows(NoSuchElementException.class, wortSequenz::naechstesWort);
    }

    @Test
    public void wortSequenzThrowsErrorOnNullText() {
        int zeilenLaenge = 5;
        Assertions.assertThrows(IllegalArgumentException.class, () -> new WortSequenz(null, zeilenLaenge));
    }

    @Test
    public void wortSequenzCorrectOnEmptyText() {
        String inputText = "          ";
        int zeilenLaenge = 5;
        WortSequenz wortSequenz = new WortSequenz(inputText, zeilenLaenge);

        Assertions.assertFalse(wortSequenz.wortVerfuegbar());
    }
}