package katas.textumbruch.v1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TextumbruchTest {

    @Test
    public void testUmbruch9Blocksatz() {
        String input = "Es blaut die Nacht, die Sternlein blinken, Schneeflöcklein leis hernieder sinken.";

        String expectedOutput =
                                "Es  blaut\n" +
                                "die\n" +
                                "Nacht,\n" +
                                "die\n" +
                                "Sternlein\n" +
                                "blinken,\n" +
                                "Schneeflö\n" +
                                "cklein\n" +
                                "leis\n" +
                                "hernieder\n" +
                                "sinken.\n";

        String actualOutput = Textumbruch.umbruch(input, 9, true);

        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testUmbruch9KeinBlocksatz() {
        String input = "Es blaut die Nacht, die Sternlein blinken, Schneeflöcklein leis hernieder sinken.";

        String expectedOutput =
                        "Es blaut\n" +
                        "die\n" +
                        "Nacht,\n" +
                        "die\n" +
                        "Sternlein\n" +
                        "blinken,\n" +
                        "Schneeflö\n" +
                        "cklein\n" +
                        "leis\n" +
                        "hernieder\n" +
                        "sinken.\n";

        String actualOutput = Textumbruch.umbruch(input, 9, false);

        Assertions.assertEquals(expectedOutput, actualOutput);
    }


    @Test
    public void testUmbruch22Blocksatz() {
        String input = "Es blaut die Nacht, die Sternlein blinken, Schneeflöcklein leis hernieder sinken.";

        String expectedOutput =
                        "Es  blaut  die  Nacht,\n" +
                        "die Sternlein blinken,\n" +
                        "Schneeflöcklein   leis\n" +
                        "hernieder      sinken.\n";

        String actualOutput = Textumbruch.umbruch(input, 22, true);
        
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testUmbruch22KeinBlocksatz() {
        String input = "Es blaut die Nacht, die Sternlein blinken, Schneeflöcklein leis hernieder sinken.";

        String expectedOutput =
                "Es blaut die Nacht,\n" +
                "die Sternlein blinken,\n" +
                "Schneeflöcklein leis\n" +
                "hernieder sinken.\n";

        String actualOutput = Textumbruch.umbruch(input, 22, false);
        
        Assertions.assertEquals(expectedOutput, actualOutput);
    }
    
}