package katas.taschenrechner.impl;

import katas.taschenrechner.Output;

public class Display extends PoweredDeviceWithSubdevices implements Output {

    String displayedValue = "0";

    @Override
    public void showNumber(int number) {
        if(hasPower()) {
            displayedValue = "" + number;
            System.out.println("Display: " + displayedValue);
        }
    }

    @Override
    public String asString() {
        return displayedValue;
    }

    @Override
    public void reset() {
        displayedValue = "0";
    }
}
