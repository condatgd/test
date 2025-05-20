package katas.domino;

import org.junit.platform.commons.util.StringUtils;

public class DominoString {

    public static boolean allDominosWillFall(String dominoString) {
        // Validate input
        if (StringUtils.isBlank(dominoString)) {
            return false;
        }

        String trimmedDominoString = dominoString.replace("_"," ").trim();
        int remainingHeight = Integer.MAX_VALUE;

        for (char c : trimmedDominoString.toCharArray()) {
            if (c == ' ') {
                if (--remainingHeight == 0) {
                    return false;
                }
            } else {
                remainingHeight = Character.getNumericValue(c);
            }
        }

        return true;
    }

    public static boolean allDominosWillFallKerstin(String dominoString) {
        // Validate input
        if (StringUtils.isBlank(dominoString)) {
            return false;
        }

        String trimmedDominoString = dominoString.replace("_"," ").trim();
        int remainingHeight = -1;
        int skip = 0;
        for (char c : trimmedDominoString.toCharArray()) {
            System.out.println("char: " + c + " skip: " + skip);
            if(skip <= 0) {
                if (c == ' ') {
                    if (--remainingHeight == 0) {
                        return false;
                    }
                } else {
                    skip = remainingHeight - 1;
                    remainingHeight = Character.getNumericValue(c);
                }
            } else {
                skip--;
                System.out.println("skip: " + skip + ", remainingHeight: " + remainingHeight);
            }
        }
        return true;
    }
}
