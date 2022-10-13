import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class VariationsTest {
    private static final short VARIABLE_COUNT = 4;
    private static final String CONTEXT_ITEM_WILDCARD = "all";
    private static final String CONTEXT_ITEM_SEPARATOR = "_";

    public static void main(String[] args) {
        test();
        List<String> list = List.of("a", "", "b", "c");

        List<List<String>> variants = list.stream()
                .map(v -> "".equals(v) ?
                        List.of("x", CONTEXT_ITEM_WILDCARD) :
                        List.of("y", CONTEXT_ITEM_WILDCARD, v)
                )
                .collect(Collectors.toList());

        System.out.println(variations(variants).collect(Collectors.toList()));
    }

    private static Stream<String> variations(List<List<String>> variants) {
        if (variants.size() == 1) {
            return variants.get(0).stream();
        } else {
            return variants.get(0).stream()
                    .flatMap(v -> variations(variants.subList(1, variants.size()))
                                    .map(p -> v + CONTEXT_ITEM_SEPARATOR + p));
        }
    }

    private static List<String> variations0(List<List<String>> variants) {
        if (variants.size() == 1) {
            return variants.get(0);
        } else {
            List<String> variations = variations0(variants.subList(1, variants.size()));
            return variants.get(0).stream()
                    .flatMap(v -> variations.stream()
                                    .map(p -> v + CONTEXT_ITEM_SEPARATOR + p))
                    .collect(Collectors.toList());
        }
    }

    private static void test() {
        String[] itemValues = new String[4];
        itemValues[0] = "a";
        itemValues[1] = null;
        itemValues[2] = "b";
        itemValues[3] = "c";
        List<String> list = IntStream.range(0, 1 << VARIABLE_COUNT)
                .mapToObj(nr -> IntStream.range(0, VARIABLE_COUNT)
                        .mapToObj(exp -> ((nr & 1 << exp) != 0 && itemValues[exp] != null) ? itemValues[exp] : CONTEXT_ITEM_WILDCARD)
                        .collect(Collectors.joining(CONTEXT_ITEM_SEPARATOR)))
                .distinct()
                .collect(Collectors.toList());
        System.out.println(list);
    }

}
