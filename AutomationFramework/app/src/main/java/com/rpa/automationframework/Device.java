package com.rpa.automationframework;

public final class Device {
    public ControlFinder controlFinder;

    private Device() {
    }

    private static final class InstanceHolder {
        private static final Device instance = new Device();
    }

    public static Device getInstance() {
        return InstanceHolder.instance;
    }
}
