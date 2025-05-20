package katas.textumbruch.v1;

public class Textumbruch {

    protected static String umbruch(String text, int zeilenLaenge, boolean blocksatz) {
        StringBuilder output = new StringBuilder();
        WortSequenz woerter = new WortSequenz(text, zeilenLaenge);
        Zeile zeile = new Zeile(zeilenLaenge);
        while(woerter.wortVerfuegbar()) {
            String wort = woerter.naechstesWort();
            boolean hinzugefuegt = zeile.versucheWortHinzuzufuegen(wort);
            if(!hinzugefuegt) {
                output.append(zeile.getInhalt(blocksatz)).append("\n");
                zeile = new Zeile(zeilenLaenge);
                zeile.versucheWortHinzuzufuegen(wort);
            }
        }
        output.append(zeile.getInhalt(blocksatz)).append("\n");
        return output.toString();
    }

}
