package katas.color_permutations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Color {
    BLUE, RED, YELLOW, X, Y;

    public static Color firstColor() {
        return BLUE;
    }
    public Color nextColor() {
        int nextIndex = this.ordinal() + 1;
        if (Color.values().length <= nextIndex)
            return firstColor();
        else
            return Color.values()[nextIndex];
    }

}
