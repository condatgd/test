package katas.textumbruch.v0;

public class Textumbruch {

    public static void main(String[] args) {
        String text = "Es blaut die Nacht,\ndie Sternlein blinken,\nSchneeflöcklein leis hernieder sinken.";

        String[] split = text.split("\\s+");

        umbruch(text,9,true);
    }

    /**
     * Breaks the given text into lines with a specified line length and alignment.
     *
     * @param text         The text to be broken into lines.
     * @param zeilenLaenge The maximum length of each line.
     * @param blocksatz    Specifies whether the text should be aligned as block text (true) or left-aligned (false).
     */
    private static void umbruch(String text, int zeilenLaenge, boolean blocksatz) {
        WortSequenz woerter = new WortSequenz(text, zeilenLaenge);
        Zeile zeile = new Zeile(zeilenLaenge, blocksatz);
        while(woerter.hasNext()) {
            if(zeile.wortPasstRein(woerter.lookAhead())) {
                zeile.addToken(woerter.next());
            } else {
                System.out.println(zeile);
                zeile = new Zeile(zeilenLaenge, blocksatz);
            }
        }
        System.out.println(zeile);
    }

}
