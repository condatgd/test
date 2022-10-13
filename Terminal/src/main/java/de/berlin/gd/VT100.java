package de.berlin.gd;

public class VT100 {
    public static void cls() {
        System.out.print("\033[2J\033[1;1H");
    }

    public static void printBlack(String s) {
        System.out.print("\u001b[30m" + s);
    }

    public static void printRed(String s) {
        System.out.print("\u001b[31m" + s);
    }

    public static void printBlue(String s) {
        System.out.print("\u001b[34m" + s);
    }

    public static void cursor(int row, int column) {
        char escCode = 0x1B;
        System.out.print(String.format("%c[%d;%df",escCode, row, column));
    }
}
