package katas.serialcomp;

public class Logic {

    public static int START = 100;
    public static int END = 200;
    public static int ADD   = 101;

    private boolean started = false;
    private boolean opAdd = false;
    private int acc=0;

    public void input(int value) {
        System.out.println("Logic got: " + value);
        if (value == START) {
            System.out.println("Starting...");
            started = true;
            opAdd = false;
        } else
        if (value == END) {
            System.out.println("Ending.");
            started = false;
            opAdd = false;
        } else
        if (started && value == ADD) {
            System.out.println("Adding ...");
            opAdd = true;
        } else
        if (started && opAdd) {
            acc += value;
            System.out.println("acc: " + acc);
        }

    }
}
