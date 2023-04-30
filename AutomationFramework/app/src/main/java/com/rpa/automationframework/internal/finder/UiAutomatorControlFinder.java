package com.rpa.automationframework.internal.finder;

import androidx.test.uiautomator.UiDevice;

public abstract class UiAutomatorControlFinder implements ControlFinder {
    protected UiDevice uiDevice;

    public UiAutomatorControlFinder(UiDevice uiDevice) {
        this.uiDevice = uiDevice;
    }
}
