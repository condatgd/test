package katas.taschenrechner;

public interface Output extends PowerableDeviceWithSubdevices {

    public void showNumber(int number);

    public String asString();
}
