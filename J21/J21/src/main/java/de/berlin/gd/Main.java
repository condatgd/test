package de.berlin.gd;

import java.util.Collection;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        ARecord aRecord = new ARecord(1, 2);
        int x;
        switch (aRecord) {
            case ARecord(int a, int b) when a==0 : x = b;  break;
            case ARecord(int a, int b) when a>0  : x = a+b; break;
            default:
                throw new IllegalStateException("Unexpected value: " + aRecord);
        }

    }

    static record ARecord (int a, int b) {}


    static Object get(Collection<String> c) {
        return switch (c) {
            case Stack<String> s when s.empty() -> s.push("first");
            case Stack<String> s2 -> s2.push("second");
            default -> c;
        };
    }
}