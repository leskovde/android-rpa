package com.rpa.automationframework.finders;

import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiSelector;

import com.rpa.automationframework.internal.types.RawUiElementUnion;

public abstract class UiAutomatorControlFinder implements ControlFinder {
    protected UiDevice uiDevice;

    public UiAutomatorControlFinder(UiDevice uiDevice) {
        this.uiDevice = uiDevice;
    }

    @Override
    public RawUiElementUnion findByControlIndex(int index) {
        UiObject control = uiDevice.findObject(new UiSelector().index(index));
        return new RawUiElementUnion(control);
    }
}
