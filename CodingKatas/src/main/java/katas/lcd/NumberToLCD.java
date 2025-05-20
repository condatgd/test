package katas.lcd;

public class NumberToLCD {

    public static void main(String[] args) {
        numbertoLCD(1234567890);
    }

    private static void numbertoLCD(int number) {
        ((String)""+number).chars().forEach(i -> printLCD(i-48));
    }

    private static void printLCD(int digit) {
        /*
               _  _     _  _  _  _  _
             | _| _||_||_ |_   ||_||_|
             ||_  _|  | _||_|  ||_| _|
         */
        boolean isNot0 = digit != 0;
        boolean isNot1 = digit != 1;
        boolean isNot2 = digit != 2;
        boolean isNot3 = digit != 3;
        boolean isNot4 = digit != 4;
        boolean isNot5 = digit != 5;
        boolean isNot6 = digit != 6;
        boolean isNot7 = digit != 7;
        boolean isNot9 = digit != 9;

        boolean isNot_1_7 = isNot1 && isNot7;
        boolean isNot_1_7_4 = isNot_1_7 && isNot4;

        boolean isNotA = isNot1 && isNot4;

        boolean isNotB1 = isNot_1_7 && isNot2 && isNot3;
        boolean isNotB2 = isNot_1_7 && isNot0;
        boolean isNotB3 = isNot5 && isNot6;

        boolean isNotC1 = isNot_1_7_4 && isNot3 && isNot5 && isNot9;
        boolean isNotC2 = isNot_1_7_4;
        boolean isNotC3 = isNot2;

        System.out.println(digit + ":" + " " + displaySegment("_", digit, isNotA) + " ");
        System.out.println(digit + ":" + displaySegment("|", digit, isNotB1) + displaySegment("_", digit, isNotB2) + displaySegment("|", digit, isNotB3));
        System.out.println(digit + ":" + displaySegment("|", digit, isNotC1) + displaySegment("_", digit, isNotC2) + displaySegment("|", digit, isNotC3));
    }

    private static String displaySegment(String c, int digit, boolean visible) {
        return visible ? c : " ";
    }

}
