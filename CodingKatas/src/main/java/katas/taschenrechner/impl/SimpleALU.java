package katas.taschenrechner.impl;

import katas.taschenrechner.Code;
import katas.taschenrechner.ALU;

public class SimpleALU extends PoweredDeviceWithSubdevices implements ALU {

    int arg1 = 0;
    int arg2 = 0;

    Code op;

    @Override
    public void number(int number) {
        if(op==null) {
            arg1 = number;
        } else {
            arg2 = number;
        }
    }

    @Override
    public void operation(Code op) {
        this.op = op;
    }

    @Override
    public boolean calculationPossible() {
        return hasPower() && op!=null;
    }

    @Override
    public int calculate() {
        int result = 0;
        if(calculationPossible()) {
            switch (op) {
                case PLUS -> result = arg1 + arg2;
                case MULT -> result = arg1 * arg2;
            }
            op = null;
            arg1 = 0;
            arg2 = 0;
        }
        return result;
    }

    @Override
    public boolean opPrefered(Code op) {
        return false;
    }

    @Override
    public void reset() {
        arg1 = 0;
        arg2 = 0;
        op = null;
    }
}
