package katas.cpu;

public class Signal {

    private final String name;
    private final int width;

    private boolean[] state;

    public Signal(String name, int width) {
        this.name = name;
        this.width = width;
        this.state = new boolean[width];
    }

    

}
