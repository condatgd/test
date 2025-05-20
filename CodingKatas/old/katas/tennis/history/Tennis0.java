package katas.tennis.history;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


public class Tennis0 {
    static enum SingleState { NONE, LOVE, FIFTEEN, THIRTY, FOURTY, DEUCE, ADV1, ADV2, WIN1, WIN2}

    @Data
    @AllArgsConstructor
    @ToString
    static class State {
        SingleState overState;
        ArrayList<SingleState> players;

        public void pointPLayer1() {
            point(0,1);
        }
        public void pointPLayer2() {
            point(1,0);
        }

        private void point(int playerIndex, int otherPlayerIndex) {
            SingleState playerState = players.get(playerIndex);
            if(overState!= SingleState.NONE) {
                switch (overState) {
                    case DEUCE -> overState = playerIndex == 0 ? SingleState.ADV1 : SingleState.ADV2;
                    case ADV1 -> overState = playerIndex == 0 ? SingleState.WIN1 : SingleState.DEUCE;
                    case ADV2 -> overState = playerIndex == 1 ? SingleState.WIN2 : SingleState.DEUCE;
                }
            } else
                switch (playerState) {
                    case LOVE -> players.set(playerIndex, SingleState.FIFTEEN);
                    case FIFTEEN -> players.set(playerIndex, SingleState.THIRTY);
                    case THIRTY -> players.set(playerIndex, SingleState.FOURTY);
                    case FOURTY -> players.set(playerIndex, playerIndex == 0 ? SingleState.WIN1 : SingleState.WIN2);
                }
            if(players.get(playerIndex).equals(SingleState.FOURTY) && players.get(otherPlayerIndex).equals(SingleState.FOURTY)) {
                overState = SingleState.DEUCE;
                players.set(playerIndex, SingleState.NONE);
                players.set(otherPlayerIndex, SingleState.NONE);
            }
        }

    }

    State state;

    public Tennis0() {
        state = new State(SingleState.NONE, new ArrayList<>(List.of(SingleState.LOVE, SingleState.LOVE)));
    }

    public void pointPlayer1() {
        state.pointPLayer1();
    }

    public void pointPlayer2() {
        state.pointPLayer2();
    }

    public String sayGameState() {
        if(state.overState!= SingleState.NONE) {
            return state.overState.toString();
        } else
            return state.players.get(0).toString() + "-" +state.players.get(1).toString();
    }


}
