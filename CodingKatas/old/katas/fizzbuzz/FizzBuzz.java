package katas.fizzbuzz;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class FizzBuzz {

    public static void main(String[] args) {
        IntStream.rangeClosed(1,30).boxed()
                .map(FizzBuzz::applyFizzBuzz)
                .forEach(System.out::println);
    }

    static BiFunction<Integer, Integer, Boolean> teilbar = (n,d) -> n % d == 0;

    static Predicate<Integer> teilbarDurch3 = n -> teilbar.apply(n,3);
    static Predicate<Integer> teilbarDurch5 = n -> teilbar.apply(n,5);

    static Predicate<Integer> teilbarDurch7 = n -> teilbar.apply(n,7);
//     static Predicate<Integer> teilbarDurch5Und3 = teilbarDurch3.and(teilbarDurch5);

    static List<Rule> rules = List.of(
            new Rule(teilbarDurch3, "fizz"),
            new Rule(teilbarDurch5, "buzz"),
            new Rule(teilbarDurch7, "toll")
    );

    @Data
    @AllArgsConstructor
    private static class Rule {       // wenn -> ergebnis
        Predicate<Integer> condition;
        String value;
    }

    private static String applyFizzBuzz(Integer n) {
        StringBuilder result = new StringBuilder();
        for(Rule rule:rules) {
            if(rule.getCondition().test(n)) {
                result.append(rule.getValue());
            }
        }
        return "".equals(result.toString()) ? n.toString() : result.toString();

/*
        String result = rules.stream()
                .filter(rule -> rule.getCondition().test(n))
                .map(Rule::getValue)
                .collect(Collectors.joining());

        return "".equals(result) ? n.toString() : result;
*/

    }





}
