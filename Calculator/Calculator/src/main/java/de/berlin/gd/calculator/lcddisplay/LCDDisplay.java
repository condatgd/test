package de.berlin.gd.calculator.lcddisplay;

import org.springframework.stereotype.Component;

@Component
public class LCDDisplay implements ReadableDisplay {

    String currentDisplay = "";

    public void show(String s) {
        System.out.println("LCD Display show: " + s);
        currentDisplay = s;
    }

    public void clear() {
        currentDisplay = "";
    }

    @Override
    public String read() {
        return currentDisplay;
    }
}
