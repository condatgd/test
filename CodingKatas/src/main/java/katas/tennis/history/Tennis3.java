package katas.tennis.history;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

public class Tennis3 {

    List<Rule> rules;

    String state = "0-0";
    public Tennis3(String player1, String player2) {
        rules = List.of(
                new Rule("win-(.*)", "win-$1"),
                new Rule("(.*)-win", "$1-win"),
                new Rule("0-(.*)", "15-$1"),
                new Rule("15-(.*)", "30-$1"),
                new Rule("30-40", "deuce-deuce"),
                new Rule("30-(.*)", "40-$1"),
                new Rule("40-(.*)", "win-$1"),
                new Rule("deuce-deuce", "adv-none"),
                new Rule("adv-none", "win-none"),
                new Rule("none-adv", "deuce-deuce")
        );
    }

    public String getScoreText() {
        if(state.startsWith("win")) return "WIN1";
        if(state.endsWith("win")) return "WIN2";
        if(state.startsWith("adv")) return "ADV1";
        if(state.endsWith("adv")) return "ADV2";
        if(state.equals("deuce-deuce")) return "DEUCE";
        return state;
    }

    public void playerOneScores() {
        state = rules.stream()
                .filter(rule -> rule.match(state))
                .findFirst()
                .map(rule -> rule.apply(state))
                .orElse(state);
    }

    public void playerTwoScores() {
        String reversedState = reverseState(state);
        state = reverseState(rules.stream().filter(rule -> rule.match(reversedState)).findFirst().map(rule -> rule.apply(reversedState)).orElse(state));
    }

    private String reverseState(String state) {
        return state.replaceAll("(.*)-(.*)","$2-$1");
    }


    @Data
    @AllArgsConstructor
    private static class Rule {
        private String head, body;

        public boolean match(String state) {
            return state.matches(head);
        }

        public String apply(String state) {
            return state.replaceAll(head,body);
        }
    }
}
