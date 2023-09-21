package katas.taschenrechner;

public interface ALU extends PowerableDeviceWithSubdevices {

    public void number(int number);
    public void operation(Code op);

    boolean calculationPossible();
    int calculate();

    boolean opPrefered(Code op);
}
