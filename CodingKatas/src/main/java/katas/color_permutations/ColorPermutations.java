package katas.color_permutations;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ColorPermutations {

    public static void main(String[] args) {
        List<List<Color>> lists = colorPermutations(10);
        System.out.println("fertig");
    }

    /*
    rgb

        [r]
        g: [gr,rg]
        b: [bgr,brg, gbr,rbg, grb,rgb]
    */
    public static List<List<Color>> colorPermutations(int n) {
        if (n == 0) {
            return List.of(List.of());
        } else {
            Stream<List<Color>> stream = colorPermutations(n - 1).stream();

            Stream<List<Color>> listStream = stream.flatMap(
                    aPerm -> Arrays.stream(Color.values())
                                .flatMap(c -> allCombinations(c, aPerm)));

            return listStream.collect(Collectors.toList());

        }
    }

    private static Stream<List<Color>> allCombinations(Color c, List<Color> perm) {
        return
                IntStream.range(0, perm.size()+1).boxed()
                .map( i -> Stream.of(
                                perm.subList(0, i),
                                List.of(c),
                                i<=perm.size() ? perm.subList(i, perm.size()) : new ArrayList<Color>()
                                )
                            .flatMap(Collection::stream)
                            .toList()
                 );
    }
}
