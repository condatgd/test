package katas.taschenrechner.impl;

import katas.taschenrechner.Code;
import katas.taschenrechner.KeyboardAdaptor;
import katas.taschenrechner.PowerableDeviceWithSubdevices;

import java.util.ArrayList;
import java.util.List;

public abstract class PoweredDeviceWithSubdevices implements PowerableDeviceWithSubdevices {

    List<PowerableDeviceWithSubdevices> subDevices;
    private PoweredDeviceWithSubdevices parentDevice;

    public PoweredDeviceWithSubdevices() {
        subDevices = new ArrayList<>();
    }

    private boolean powered;

    @Override
    public void switchPower(boolean on) {
        powered = on;
        reset();
        subDevices.forEach(subDevice -> subDevice.switchPower(on));
    }

    @Override
    public boolean hasPower() {
        return powered;
    }

    @Override
    public void addSubdevice(PowerableDeviceWithSubdevices subDevice) {
        subDevice.setParentDevice(this);
        subDevices.add(subDevice);
    }

    @Override
    public void setParentDevice(PoweredDeviceWithSubdevices parentDevice) {
        this.parentDevice = parentDevice;
    }

    @Override
    public PoweredDeviceWithSubdevices getParentDevice() {
        return this.parentDevice;
    }

    public void sendMessageToParent(Code code) {
        ((KeyboardAdaptor)getParentDevice()).enterCode(code);
    }


}
