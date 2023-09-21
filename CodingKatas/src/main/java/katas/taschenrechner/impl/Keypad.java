package katas.taschenrechner.impl;

import katas.taschenrechner.Code;
import katas.taschenrechner.Input;

public class Keypad extends PoweredDeviceWithSubdevices implements Input {

    @Override
    public void enterCode(Code code) {
        if(hasPower()) {
            System.out.println("Keypad: " + code);
            sendMessageToParent(code);
        }
    }


    @Override
    public void reset() {}
}
