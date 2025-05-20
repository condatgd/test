package katas.serialcomp;

public class SerialComp {

    public static void main(String[] args) {
        ShiftStorage shiftStorage = new ShiftStorage(10);
        shiftStorage.clear();
        shiftStorage.setCurrentValue(Logic.START); shiftStorage.shift();
        shiftStorage.setCurrentValue(Logic.ADD);   shiftStorage.shift();
        shiftStorage.setCurrentValue(1); shiftStorage.shift();
        shiftStorage.setCurrentValue(2); shiftStorage.shift();
        shiftStorage.setCurrentValue(Logic.END);   shiftStorage.shift();

        Logic logic = new Logic();

        while(true) {
            shiftStorage.shift();
            logic.input(shiftStorage.getCurrentValue());
        }
    }
}
