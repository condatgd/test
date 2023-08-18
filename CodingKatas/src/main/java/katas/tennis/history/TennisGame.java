package katas.tennis.history;

import org.projog.api.Projog;
import org.projog.api.QueryResult;

import java.io.File;

public class TennisGame {
    private final String player1;
    private final String player2;

    private String player1State;
    private String player2State;

    Projog prolog;

    public TennisGame(String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
        player1State = "LOVE";
        player2State = "LOVE";
        prolog = new Projog();
        prolog.consultFile(new File("src/main/resources/tennis_game.pl"));
    }

    public Object getScoreText() {
        QueryResult queryResult = prolog.executeQuery("say('" + player1State + "','" + player2State + "', SAY).");
        if (queryResult.next()) {
            return queryResult.getTerm("SAY").toString();
        } else {
            return "dont know what to say ...";
        }
    }

    public void playerOneScores() {
        scorePlayer(1);
    }

    public void playerTwoScores() {
        scorePlayer(2);
    }

    private void scorePlayer(int playerNum) {
        // System.out.println("score: " + playerNum);
        QueryResult queryResult;
        if (playerNum == 1) {
            queryResult = prolog.executeQuery("transition('" + player1State + "','" + player2State + "', NEXT1,NEXT2).");
        } else {
            queryResult = prolog.executeQuery("transition('" + player2State + "','" + player1State + "', NEXT2,NEXT1).");
        }
        if (queryResult.next()) {
            player1State = queryResult.getTerm("NEXT1").toString();
            player2State = queryResult.getTerm("NEXT2").toString();
        }
        // System.out.println("player1State: " + player1State);
        // System.out.println("player2State: " + player2State);
    }
}
