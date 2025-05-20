package de.berlin.gd.calculator.brailledisplay;

import de.berlin.gd.calculator.lcddisplay.ReadableDisplay;
import org.springframework.stereotype.Component;

@Component
public class BrailleDisplay implements ReadableDisplay {

    String currentDisplay = "";

    public void show(String s) {
        System.out.println("Braille Display show: " + s);
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
