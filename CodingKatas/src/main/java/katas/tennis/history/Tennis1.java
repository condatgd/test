package katas.tennis.history;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


public class Tennis1 {
    enum StateTransition {
        LOVE("FIFTEEN", "FIFTEEN"),
        FIFTEEN("THIRTY", "THIRTY"),
        THIRTY("FOURTY", "FOURTY"),
        FOURTY("WIN1", "WIN2"),
        DEUCE("ADV1", "ADV2"),
        ADV1("WIN1", "DEUCE"),
        ADV2("DEUCE", "WIN2"),
        WIN1("", ""),
        WIN2("", ""),
        NONE("", "");

        private final List<String> next;

        StateTransition(String next1, String next2) {
            this.next = List.of(next1, next2);
        }

        public StateTransition getNext(int playerIndex) {
            if(next.get(playerIndex).length()>0)
                return StateTransition.valueOf(next.get(playerIndex));
            else
                return this;
        }
    }

    @Data
    @AllArgsConstructor
    static class TennisState {
        StateTransition overState;
        ArrayList<StateTransition> players;

        public void pointPLayer1() {
            point(0,1);
        }
        public void pointPLayer2() {
            point(1,0);
        }

        private void point(int playerIndex, int otherPlayerIndex) {
            StateTransition playerState = players.get(playerIndex);
            if(overState!= StateTransition.NONE) {
                overState = overState.getNext(playerIndex);
            } else {
                players.set(playerIndex, playerState.getNext(playerIndex));
            }
            if(players.get(playerIndex).equals(StateTransition.FOURTY) && players.get(otherPlayerIndex).equals(StateTransition.FOURTY)) {
                overState = StateTransition.DEUCE;
                players.set(playerIndex, StateTransition.NONE);
                players.set(otherPlayerIndex, StateTransition.NONE);
            }
        }

        public String toString() {
            if(getOverState()!= StateTransition.NONE) {
                return getOverState().toString();
            } else
                return getPlayers().get(0).toString() + "-" + getPlayers().get(1).toString();
        }

    }

    TennisState tennisState;

    public Tennis1() {
        tennisState = new TennisState(StateTransition.NONE, new ArrayList<>(List.of(StateTransition.LOVE, StateTransition.LOVE)));
    }

    public void pointPlayer1() {
        tennisState.pointPLayer1();
    }
    public void pointPlayer2() {
        tennisState.pointPLayer2();
    }
    public String sayGameState() {
        return tennisState.toString();
    }

}
