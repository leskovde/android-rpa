package com.rpa.automationframework.finders;

import androidx.test.uiautomator.UiDevice;

public abstract class UiAutomatorControlFinder implements ControlFinder {
    protected UiDevice uiDevice;

    public UiAutomatorControlFinder(UiDevice uiDevice) {
        this.uiDevice = uiDevice;
    }
}
