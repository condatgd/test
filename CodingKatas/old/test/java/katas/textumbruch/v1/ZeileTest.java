package katas.textumbruch.v1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ZeileTest {
    // Setting maximum length for the line
    final int MAXIMUM_LINE_LENGTH = 10;

    @Test
    public void testVersucheWortHinzuzufuegen() {
        // Create a new line with given maximum line length
        Zeile testZeile = new Zeile(MAXIMUM_LINE_LENGTH);

        String shortWord = "short";
        Assertions.assertTrue(testZeile.versucheWortHinzuzufuegen(shortWord), "The word should have been added");

        String longWord = "ThisIsAVeryLongWord";
        Assertions.assertFalse(testZeile.versucheWortHinzuzufuegen(longWord), "The word should not have been added");
    }

    @Test
    public void testGetInhalt() {
        // Create a new line with given maximum line length
        Zeile zeile = new Zeile(MAXIMUM_LINE_LENGTH+1);
        zeile.versucheWortHinzuzufuegen("Hello");
        zeile.versucheWortHinzuzufuegen("world");

        String expectedVal = "Hello world";
        String actualVal = zeile.getInhalt(false);
        Assertions.assertEquals(expectedVal, actualVal, "The contents should match");

        // Check the block text
        String expectedBlockText = "Hello world";
        String actualBlockText = zeile.getInhalt(true);
        Assertions.assertEquals(expectedBlockText, actualBlockText, "The block texts should match");
    }

    @Test
    public void testPlatzVerfuegbar() {
        // Create a new line with given maximum line length
        Zeile testZeile = new Zeile(MAXIMUM_LINE_LENGTH);

        testZeile.versucheWortHinzuzufuegen("data");

        int actualValue = testZeile.platzVerfuegbar(0);
        Assertions.assertEquals(5,actualValue, "The available space should match");

        // Test negative offset
        Assertions.assertThrows(IllegalArgumentException.class, () -> testZeile.platzVerfuegbar(-10));
    }
}