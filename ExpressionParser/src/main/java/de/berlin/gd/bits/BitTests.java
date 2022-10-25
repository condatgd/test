package de.berlin.gd.bits;

public class BitTests {

    public static void main(String[] args) {
        int n = 1778;
        System.out.println(printBinary(n,8));
        System.out.println(printBinary(n << 1, 8));
    }

    public static String printBinary(int n, int len) {
        String s = Integer.toString(n, 2);
        int rc = len - s.length();

        return "0".repeat(rc) + s;
    }
}
