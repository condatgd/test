package katas.mastermind_tdd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Color {
    BLUE("b"), RED("r"), GREEN("g"), PINK("p"), YELLOW("y"), PURPLE("u"), _blank(" ");

    private final String cstr;

    Color(String color) {
        this.cstr = color;
    }

    public static Color colorByValue(String c) {
        return Arrays.stream(values())
                .filter(value -> value.cstr.equals(c))
                .findFirst().orElse(null);
    }

    public static List<Color> strToColorList(String str) {
        List<Color> list = new ArrayList<>();
        for (char c : str.toCharArray()) {
            list.add(colorByValue(String.valueOf(c)));
        }
        return list;
    }
}
