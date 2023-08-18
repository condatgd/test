package katas.tennis.history;

import org.projog.api.Projog;
import org.projog.api.QueryResult;

import java.io.File;

public class TennisProlog {

    Projog prolog;
    String player1;
    String player2;
    public TennisProlog() {
        player1 = "LOVE";
        player2 = "LOVE";
        prolog = new Projog();
        prolog.consultFile(new File("src/main/resources/tennis.pl"));
    }

    private void next(int playerNum) {
        QueryResult queryResult;
        if(playerNum==1) {
            queryResult = prolog.executeQuery("rule('" + player1 + "','" + player2 + "', NEXT1,NEXT2).");
        } else {
            queryResult = prolog.executeQuery("rule('" + player2 + "','" + player1 + "', NEXT2,NEXT1).");
        }
        if (queryResult.next()) {
            player1 = queryResult.getTerm("NEXT1").toString();
            player2 = queryResult.getTerm("NEXT2").toString();
        }
    }

    public String sayGameState() {
        QueryResult queryResult = prolog.executeQuery("say('" + player1 + "','" + player2 + "', SAY).");
        if (queryResult.next()) {
            return queryResult.getTerm("SAY").toString();
        } else {
            return "dont know what to say ...";
        }
    }

    public void pointPlayer1() {
        next(1);
    }

    public void pointPlayer2() {
        next(2);
    }
}
