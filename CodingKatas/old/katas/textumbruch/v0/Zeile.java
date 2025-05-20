package katas.textumbruch.v0;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Zeile {
    private final List<String> tokenWithSpaces;
    private final int zeilenLaenge;
    private final boolean blocksatz;
    @Getter
    private int len;

    public Zeile(int zeilenLaenge, boolean blocksatz) {
        this.zeilenLaenge = zeilenLaenge;
        this.blocksatz = blocksatz;
        tokenWithSpaces = new ArrayList<>();
        len = 0;
    }

    public boolean wortPasstRein(String wort) {
        return isEmpty() ||
               getLen() + 1 + wort.length() <= zeilenLaenge;
    }

    public void addToken(String token) {
        if(len>0) {
            tokenWithSpaces.add(" ");
            len++;
        }
        tokenWithSpaces.add(token);
        len += token.length();
    }

    public String toString() {
        return String.join("", tokenWithSpaces);
    }

    public boolean isEmpty() {
        return len==0;
    }
}
