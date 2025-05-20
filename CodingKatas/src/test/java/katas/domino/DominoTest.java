package katas.domino;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DominoTest {

    @Test
    void keineSteine() {
        Domino.Reihe reihe = new Domino.Reihe();
        reihe.start();
        assertEquals(0, reihe.getUmgefalleneSteine());
    }

    @Test
    void einStein_faellt() {
        Domino.Reihe reihe = new Domino.Reihe();
        reihe.add(new Domino.DominoStein(1,1, true));
        reihe.start();
        assertEquals(1, reihe.getUmgefalleneSteine());
    }

    @Test
    void einStein_faellt_nicht() {
        Domino.Reihe reihe = new Domino.Reihe();
        reihe.add(new Domino.DominoStein(1,1, false));
        reihe.start();
        assertEquals(0, reihe.getUmgefalleneSteine());
    }

    @Test
    void zweiSteine_fallen_ohne_Luecke() {
        Domino.Reihe reihe = new Domino.Reihe();
        reihe.add(new Domino.DominoStein(1,1, true));
        reihe.add(new Domino.DominoStein(1,1, true));
        reihe.start();
        assertEquals(2, reihe.getUmgefalleneSteine());
    }

    @Test
    void zweiSteine_fallen_mit_Luecke() {
        Domino.Reihe reihe = new Domino.Reihe();
        reihe.add(new Domino.DominoStein(2,1, true));
        reihe.add(new Domino.Abstand(1));
        reihe.add(new Domino.DominoStein(2,1, true));
        reihe.start();
        assertEquals(2, reihe.getUmgefalleneSteine());
    }

    @Test
    void zweiSteine_fallen_nicht_mit_Luecke() {
        Domino.Reihe reihe = new Domino.Reihe();
        reihe.add(new Domino.DominoStein(1,1, true));
        reihe.add(new Domino.Abstand(1));
        reihe.add(new Domino.DominoStein(2,1, true));
        reihe.start();
        assertEquals(1, reihe.getUmgefalleneSteine());
    }

    @Test
    void dreiSteine_fallen_mit_Luecke() {
        Domino.Reihe reihe = new Domino.Reihe();
        reihe.add(new Domino.DominoStein(3,1, true));
        reihe.add(new Domino.Abstand(2));
        reihe.add(new Domino.DominoStein(2,1, true));
        reihe.add(new Domino.Abstand(1));
        reihe.add(new Domino.DominoStein(2,1, true));
        reihe.start();
        assertEquals(3, reihe.getUmgefalleneSteine());
    }

}
