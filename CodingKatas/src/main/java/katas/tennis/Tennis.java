package katas.tennis;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Tennis {

    private State state;
    private List<Rule> rules;

    public Tennis() {
        state = new State();
        addRule(RuleAtom.WIN, RuleAtom.X,         RuleAtom.WIN, RuleAtom.X);
        addRule(RuleAtom.X, RuleAtom.WIN,         RuleAtom.X, RuleAtom.WIN);
        addRule(RuleAtom.ZERO, RuleAtom.X,        RuleAtom.FIFTEEN, RuleAtom.X);
        addRule(RuleAtom.FIFTEEN, RuleAtom.X,     RuleAtom.THIRTY, RuleAtom.X);
        addRule(RuleAtom.THIRTY, RuleAtom.FOURTY, RuleAtom.DEUCE, RuleAtom.DEUCE);
        addRule(RuleAtom.THIRTY, RuleAtom.X,      RuleAtom.FOURTY, RuleAtom.X);
        addRule(RuleAtom.FOURTY, RuleAtom.X,      RuleAtom.WIN, RuleAtom.X);
        addRule(RuleAtom.DEUCE, RuleAtom.DEUCE,   RuleAtom.ADVANTAGE, RuleAtom.NONE);
        addRule(RuleAtom.ADVANTAGE, RuleAtom.NONE, RuleAtom.WIN, RuleAtom.NONE);
        addRule(RuleAtom.NONE, RuleAtom.ADVANTAGE, RuleAtom.DEUCE, RuleAtom.DEUCE);
    }

    public String getScoreText() {
        return "";
    }

    public void playerOneScores() {
        rules.stream()
                .map(rule -> rule.match(state))
                .filter(Rule.Binding::isBound)
                .map(binding -> binding.getRule().apply(state))
                .findFirst()
                .orElse(state);
    }

    public void playerTwoScores() {
    }

    enum RuleAtom {
        NONE, ZERO, FIFTEEN, THIRTY, FOURTY, DEUCE, ADVANTAGE, WIN, X
    }

    private void addRule(RuleAtom h1, RuleAtom h2, RuleAtom b1, RuleAtom b2) {
        rules.add(new Rule(h1,h2,b1,b2));
    }


    @AllArgsConstructor
    @Data
    class Rule {
        private RuleAtom h1, h2, b1, b2;

        public Binding match(State state) {
            List<Binding> unifications = List.of(
                    unify(h1, state.getPlayer1State()),
                    unify(h2, state.getPlayer2State())
            );
            return new Binding(
                    unifications.stream().allMatch(u -> u.bound),
                    unifications.stream().flatMap(u -> u.getBindings().stream()).collect(Collectors.toSet()),
                    this
            );
        }

        private Binding unify(RuleAtom ruleAtom, State.PlayerState playerState) {
            if(ruleAtom.equals(RuleAtom.X))
                return new Binding(true,Set.of(playerState), null);
            else
                return new Binding(ruleAtom.name().equals(playerState.name()),Set.of(), null);
        }

        public Object apply(State state) {
            List<Binding> unifications = List.of(
                    unify(b1, state.getPlayer1State()),
                    unify(b2, state.getPlayer2State())
            );
            return new Binding(
                    unifications.stream().allMatch(u -> u.bound),
                    unifications.stream().flatMap(u -> u.getBindings().stream()).collect(Collectors.toSet()),
                    this
            );
        }

        @AllArgsConstructor
        @Data
        class Binding {
            private boolean bound;
            private Set<State.PlayerState> bindings;
            private Rule rule;
        }

    }

    @AllArgsConstructor
    @Data
    static class State {
        private PlayerState player1State, player2State;

        public State() {
            player1State = PlayerState.ZERO;
            player2State = PlayerState.ZERO;
        }
        
        public enum PlayerState {
            NONE, ZERO, FIFTEEN, THIRTY, FOURTY, DEUCE, ADVANTAGE, WIN
        }
    }
}
