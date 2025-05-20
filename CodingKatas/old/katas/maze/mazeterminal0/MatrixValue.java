package katas.maze.mazeterminal0;

import java.util.Arrays;
import java.util.Optional;

public enum MatrixValue {
    FREE(' '),
    START('1'),
    WAVE1('2'),
    WAVE2('3'),
    WAVE3('4'),

    WAY('.'),

    END('X'),
    WALL('█');

    public char getDisplayChar() {
        return c;
    }

    private final char c;

    MatrixValue(char c) {
        this.c = c;
    }

    public static Optional<MatrixValue> getValueForChar(char c) {
        return Arrays.stream(MatrixValue.values()).filter(v -> v.c==c).findFirst();
    }
}
