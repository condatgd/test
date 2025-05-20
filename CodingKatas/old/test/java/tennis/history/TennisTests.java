package tennis.history;

import katas.tennis.history.TennisProlog;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TennisTests {

    @Test
    void test1() {
        TennisProlog tennisGame = new TennisProlog();
        assertEquals("LOVE-LOVE", tennisGame.sayGameState());
    }

    @Test
    void test2() {
        TennisProlog tennisGame = new TennisProlog();
        tennisGame.pointPlayer2();
        assertEquals("LOVE-FIFTEEN", tennisGame.sayGameState());
    }

    @Test
    void test3() {
        TennisProlog tennisGame = new TennisProlog();
        tennisGame.pointPlayer1();
        tennisGame.pointPlayer1();
        tennisGame.pointPlayer1();
        tennisGame.pointPlayer1();
        tennisGame.pointPlayer1();
        assertEquals("WIN1-LOVE", tennisGame.sayGameState());
    }

    @Test
    void test4() {
        TennisProlog tennisGame = new TennisProlog();
        tennisGame.pointPlayer1();
        tennisGame.pointPlayer1();
        tennisGame.pointPlayer1();
        tennisGame.pointPlayer2();
        tennisGame.pointPlayer2();
        assertEquals("FOURTY-THIRTY", tennisGame.sayGameState());
    }

    @Test
    void test5() {
        TennisProlog tennisGame = new TennisProlog();
        tennisGame.pointPlayer1();
        tennisGame.pointPlayer1();
        tennisGame.pointPlayer1();
        tennisGame.pointPlayer2();
        tennisGame.pointPlayer2();
        tennisGame.pointPlayer2();
        assertEquals("DEUCE", tennisGame.sayGameState());
    }

    @Test
    void test6() {
        TennisProlog tennisGame = new TennisProlog();
        tennisGame.pointPlayer1();
        tennisGame.pointPlayer1();
        tennisGame.pointPlayer1();
        tennisGame.pointPlayer2();
        tennisGame.pointPlayer2();
        tennisGame.pointPlayer2();
        tennisGame.pointPlayer2();
        assertEquals("ADV2", tennisGame.sayGameState());
    }

    @Test
    void test7() {
        TennisProlog tennisGame = new TennisProlog();
        tennisGame.pointPlayer1();
        tennisGame.pointPlayer1();
        tennisGame.pointPlayer1();
        tennisGame.pointPlayer2();
        tennisGame.pointPlayer2();
        tennisGame.pointPlayer2();
        tennisGame.pointPlayer2();
        tennisGame.pointPlayer1();
        assertEquals("DEUCE", tennisGame.sayGameState());
    }

    @Test
    void test8() {
        TennisProlog tennisGame = new TennisProlog();
        tennisGame.pointPlayer1();
        tennisGame.pointPlayer1();
        tennisGame.pointPlayer1();
        tennisGame.pointPlayer2();
        tennisGame.pointPlayer2();
        tennisGame.pointPlayer2();
        tennisGame.pointPlayer2();
        tennisGame.pointPlayer1();
        tennisGame.pointPlayer1();
        tennisGame.pointPlayer1();
        assertEquals("WIN1", tennisGame.sayGameState());
    }

}
