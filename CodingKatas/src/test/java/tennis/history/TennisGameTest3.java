package tennis.history;

import katas.tennis.history.Tennis3;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TennisGameTest3 {

    @ParameterizedTest
    @CsvSource(value = {
            "0, 0-0",
            "1, 15-15",
            "2, 30-30",
            "3, DEUCE",
            "4, DEUCE",
            "5, DEUCE",
            "6, DEUCE",
            "7, DEUCE",
    })
    void whenPlayerHaveSameScore(int playerScore, String assertedScoreText) {
        Tennis3 game = createScore(playerScore, playerScore);

        assertEquals(assertedScoreText, game.getScoreText());
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1, 15-0",
            "2, 30-15",
            "3, 40-30",
            "4, ADV1",
            "5, ADV1",
            "6, ADV1",
            "7, ADV1",
    })
    void whenPlayerOneHasOnePointMoreThanPlayerTwo(int playerOneScore, String assertedScoreText) {
        Tennis3 game = createScore(playerOneScore, playerOneScore - 1);

        assertEquals(assertedScoreText, game.getScoreText());
    }

    @ParameterizedTest
    @CsvSource(value = {
            "0, 0-15",
            "1, 15-30",
            "2, 30-40",
            "3, ADV2",
            "4, ADV2",
            "5, ADV2",
            "6, ADV2",
            "7, ADV2",
    })
    void whenPlayerOneHasOnePointLessThanPlayerTwo(int playerOneScore, String assertedScoreText) {
        Tennis3 game = createScore(playerOneScore, playerOneScore + 1);

        assertEquals(assertedScoreText, game.getScoreText());
    }

    @ParameterizedTest
    @CsvSource(value = {
            "2, 30-0",
            "3, 40-15",
            "4, WIN1",
            "5, WIN1",
            "6, WIN1",
            "7, WIN1",
    })
    void whenPlayerOneHasTwoPointsMoreThanPlayerTwo_thenPlayerOneWins(int playerOneScore, String assertedScoreText) {
        Tennis3 game = createScore(playerOneScore, playerOneScore - 2);

        assertEquals(assertedScoreText, game.getScoreText());
    }

    @ParameterizedTest
    @CsvSource(value = {
            "0, 0-30",
            "1, 15-40",
            "2, WIN2",
            "3, WIN2",
            "4, WIN2",
            "5, WIN2",
            "6, WIN2",
            "7, WIN2",
    })
    void whenPlayerOneHasTwoPointsLessThanPlayerTwo_thenPlayerTwoWins(int playerOneScore, String assertedScoreText) {
        Tennis3 game = createScore(playerOneScore, playerOneScore + 2);

        assertEquals(assertedScoreText, game.getScoreText());
    }

    private Tennis3 createScore(int playerOneScore, int playerTwoScore) {
        Tennis3 game = new Tennis3("player1", "player2");
        int min = Math.min(playerOneScore, playerTwoScore);
        IntStream.range(0, min).forEach(i -> {
            game.playerOneScores();
            game.playerTwoScores();
        });
        if(playerOneScore>min)
            IntStream.range(0, playerOneScore-min).forEach(i -> game.playerOneScores());
        if(playerTwoScore>min)
            IntStream.range(0, playerTwoScore-min).forEach(i -> game.playerTwoScores());
        return game;
    }
}
