package katas.taschenrechner.impl;

import katas.taschenrechner.*;
import lombok.Data;

@Data
public class TRechnerV0 extends PoweredDeviceWithSubdevices implements Calculator, KeyboardAdaptor {
    private Input input;
    private Output output;
    private ALU alu;

    int displayNumber;

    public TRechnerV0() {
        input = new Keypad();
        output = new Display();
        alu = new PrecedenceALU();

        addSubdevice(input);
        addSubdevice(output);
        addSubdevice(alu);

        displayNumber = 0;
    }

    public void reset() {
        displayNumber = 0;
    }

    @Override
    public void enterCode(Code code) {
        handleInput(code);
    }

    private void handleInput(Code code) {
        if(code.isOp()) {
            if (code == Code.EQ) {
                equalsOperation();
            } else {
                operation(code);
            }
        } else {
            addDigitToDisplay(code);
        }
    }

    private void operation(Code op) {
        if(!alu.opPrefered(op)) {
            equalsOperation();
            alu.operation(op);
            alu.number(displayNumber);
            displayNumber = 0;
        } else {
            alu.operation(op);
            displayNumber = 0;
        }
    }

    private void equalsOperation() {
        // 2+3*3+2
        alu.number(displayNumber);
        if(alu.calculationPossible()) {
            displayNumber = alu.calculate();
        }
        output.showNumber(displayNumber);
    }

    private void addDigitToDisplay(Code code) {
        displayNumber = displayNumber * 10 + code.getDigit();
        output.showNumber(displayNumber);
    }

}
