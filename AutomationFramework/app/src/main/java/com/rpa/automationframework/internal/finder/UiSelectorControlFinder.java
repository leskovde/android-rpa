package com.rpa.automationframework.internal.finder;

import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiSelector;

import com.rpa.automationframework.internal.types.RawUiElementUnion;

public class UiSelectorControlFinder extends UiAutomatorControlFinder {

    public UiSelectorControlFinder(UiDevice uiDevice) {
        super(uiDevice);
    }

    @Override
    public RawUiElementUnion findByControlIndex(int index) {
        UiObject control = uiDevice.findObject(new UiSelector().index(index));
        return new RawUiElementUnion(control);
    }

    @Override
    public RawUiElementUnion findByResourceId(String resourceId) {
        return null;
    }

    @Override
    public RawUiElementUnion findByClassName(String className) {
        return null;
    }

    @Override
    public RawUiElementUnion findByDescription(String description) {
        return null;
    }
}
