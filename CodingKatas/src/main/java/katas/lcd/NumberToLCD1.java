package katas.lcd;

import java.util.List;
import java.util.stream.Collectors;

public class NumberToLCD1 {
    private static final int LCD_ROWS = 3;
    private static final int BITS_USED = 7;

    private static final byte[] lcdBits = {
            (byte) 0b0_1111011, // 0
            (byte) 0b0_1001000, // 1
            (byte) 0b0_0111101, // 2
            (byte) 0b0_1101101, // 3
            (byte) 0b0_1001110, // 4
            (byte) 0b0_1100111, // 5
            (byte) 0b0_1110111, // 6
            (byte) 0b0_1001001, // 7
            (byte) 0b0_1111111, // 8
            (byte) 0b0_1101111  // 9
    };

    private static final boolean[] segmentVisibility = new boolean[lcdBits.length * BITS_USED];

    static {
        initializeSegmentVisibility();
    }

    public static void main(String[] args) {
        renderLCDRepresentation(1234567890987654321L);
    }

    private static void renderLCDRepresentation(long number) {
        String numberStr = String.valueOf(number);
        List<LCD7Segments> lcdSegments = numberStr.chars()
                .map(Character::getNumericValue)
                .mapToObj(digit -> new LCD7Segments(digit, segmentVisibility))
                .toList();

        for (int rowNumber = 1; rowNumber <= LCD_ROWS; rowNumber++) {
            int finalRowNumber = rowNumber;
            System.out.println(
                    lcdSegments.stream()
                            .map(lcd -> lcd.renderRow(finalRowNumber) + " ")
                            .collect(Collectors.joining())
            );
        }
    }

    private record LCD7Segments(int digit, boolean[] segmentVisibility) {
        private String renderRow(int index) {
            return switch (index) {
                case 1 -> " " +
                        renderSegment(0, "_") +
                        " ";
                case 2 -> renderSegment(1, "|") +
                        renderSegment(2,   "_") +
                        renderSegment(3,   "|");
                case 3 -> renderSegment(4, "|") +
                        renderSegment(5,   "_") +
                        renderSegment(6,   "|");
                default -> throw new IllegalStateException("Unexpected value: " + index);
            };
        }

        private String renderSegment(int segmentIndex, String c) {
            return segmentVisibility[digit * BITS_USED + segmentIndex] ? c : " ";
        }
    }

    private static void initializeSegmentVisibility() {
        for (int digit = 0; digit < lcdBits.length; digit++) {
            byte lcdBitPattern = lcdBits[digit];
            for (int bit = 0; bit < BITS_USED; bit++) {
                segmentVisibility[digit * BITS_USED + bit] = (lcdBitPattern & (1 << bit)) != 0;
            }
        }
    }
}
