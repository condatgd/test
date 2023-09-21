package katas.calculator;

import lombok.Data;

@Data
public class Calculator {

    String state = "enter a second number";

    int displayedNumber;
    private int opFirstNumber;
    private Operation operation;

    public void enterNumber(int number) {
        displayedNumber = number;
    }

    public void pressPlus() {
        pressEquals();
        opFirstNumber = displayedNumber;
        operation = Operation.PLUS;
    }

    public void pressEquals() {
        if(operation==Operation.PLUS) {
            int oldDisplayedNumber = displayedNumber;
            displayedNumber = opFirstNumber + displayedNumber;
            opFirstNumber   = oldDisplayedNumber;
        }
    }

    enum Operation {NONE, PLUS}

}
