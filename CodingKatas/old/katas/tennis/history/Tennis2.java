package katas.tennis.history;

import java.util.ArrayList;
import java.util.List;

public class Tennis2 {

    enum StateTransition {
        LOVE("FIFTEEN","FIFTEEN"),
        FIFTEEN("THIRTY","THIRTY"),
        THIRTY("FOURTY","FOURTY"),
        FOURTY("WIN1","WIN2"),
        DEUCE("ADV1","ADV2"),
        ADV1("WIN1","DEUCE"),
        ADV2("DEUCE", "WIN2"),
        WIN1("",""),
        WIN2("",""),
        NONE("",""),
        ;

        final List<String> next;

        StateTransition(String next1, String next2) {
            this.next = List.of(next1, next2);
        }

        public StateTransition nextState(int indexPlayer) {
            if(next.get(indexPlayer).length()>0)
                return valueOf(next.get(indexPlayer));
            else
                return this;
        }
    }

    StateTransition overState;
    ArrayList<StateTransition> playerStates;

    public Tennis2() {
        overState = StateTransition.NONE;
        playerStates = new ArrayList<>(List.of(StateTransition.LOVE, StateTransition.LOVE));
    }

    private void point(int indexPlayer) {
        if(overState== StateTransition.NONE)  {
            playerStates.set(indexPlayer, playerStates.get(indexPlayer).nextState(indexPlayer));
        } else {
            overState = overState.nextState(indexPlayer);
        }
        if(playerStates.get(0).equals(StateTransition.FOURTY) && playerStates.get(1).equals(StateTransition.FOURTY)) {
            overState = StateTransition.DEUCE;
            playerStates = new ArrayList<>(List.of(StateTransition.NONE, StateTransition.NONE));
        }
    }

    public String sayGameState() {
        if(overState== StateTransition.NONE)  {
            return playerStates.get(0) + "-" + playerStates.get(1);
        } else {
            return overState.toString();
        }
    }

    public void pointPlayer1() {
        point(0);
    }

    public void pointPlayer2() {
        point(1);
    }


}
