package de.berlin.gd;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        VT100.cls();
        VT100.printRed("Hello, World!");
        for(int j=1; j<10; j++) {
            for (int i = 1; i < 10; i++) {
                VT100.cursor(i, i);
                VT100.printBlack("" + i +"-" + j);
                Thread.sleep(100);
            }
        }
        VT100.printBlue("Hello, World1!");
    }



}
