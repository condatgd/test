package katas.taschenrechner;

import katas.taschenrechner.impl.PoweredDeviceWithSubdevices;

public interface PowerableDeviceWithSubdevices {
    void switchPower(boolean on);

    void reset();

    boolean hasPower();

    void addSubdevice(PowerableDeviceWithSubdevices subDevice);
    void setParentDevice(PoweredDeviceWithSubdevices poweredDeviceWithSubdevices);

    PoweredDeviceWithSubdevices getParentDevice();
}
