package com.rpa.automationframework.internal.finder;

import androidx.test.uiautomator.UiDevice;

import com.rpa.automationframework.internal.types.RawUiElementUnion;

public class BySelectorControlFinder extends UiAutomatorControlFinder {

    public BySelectorControlFinder(UiDevice uiDevice) {
        super(uiDevice);
    }

    @Override
    public RawUiElementUnion findByControlIndex(int index) {
        return null;
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
