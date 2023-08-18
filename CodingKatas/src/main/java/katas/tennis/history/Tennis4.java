package katas.tennis.history;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

public class Tennis4 {

    String state;

    List<Rule> rules;

    public Tennis4() {
        state = "0-0";
        rules = List.of(
                new Rule("win-(.*)","win-$1"),
                new Rule("(.*)-win","$1-win"),
                new Rule("0-(.*)","15-$1"),
                new Rule("15-(.*)","30-$1"),
                new Rule("30-40","deuce-deuce"),
                new Rule("30-(.*)","40-$1"),
                new Rule("40-(.*)","win-$1"),
                new Rule("deuce-deuce","adv-none"),
                new Rule("adv-none","win-none"),
                new Rule("none-adv","deuce-deuce")
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
        state = rules.stream().filter(rule -> rule.match(state)).map(rule -> rule.apply(state)).findFirst().orElse(state);
    }

    public void playerTwoScores() {
        String reversedOldState = reverse(state);
        state = reverse(rules.stream().filter(rule -> rule.match(reversedOldState)).map(rule -> rule.apply(reversedOldState)).findFirst().orElse(state));
    }

    private String reverse(String state) {
        return state.replaceAll("(.*)-(.*)", "$2-$1");
    }

    @Data
    @AllArgsConstructor
    private static class Rule {
        String head, body;

        public boolean match(String state) {
            return state.matches(head);
        }

        public String apply(String state) {
            return state.replaceAll(head,body);
        }
    }
}
