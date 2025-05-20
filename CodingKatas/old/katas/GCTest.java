package katas;

import java.util.WeakHashMap;

public class GCTest {
    WeakHashMap<String,String> weakHashMap = new WeakHashMap<>();

    public static void main(String[] args) {
        GCTest gcTest = new GCTest();
        for(int i=0; i<4000; i++) {
            gcTest.pump(i, 1);
        }
    }

    private void pump(int i1, int mb) {
        for(int i2=0; i2<1000; i2++) {
            weakHashMap.put(""+i1+"_"+i2, "x".repeat(mb*1_000_000));
        }
    }
}
