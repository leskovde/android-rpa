package com.rpa.automationframework.finders;

import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject2;


import com.rpa.automationframework.internal.types.RawUiElementUnion;

public class BySelectorControlFinder extends UiAutomatorControlFinder {

    public BySelectorControlFinder(UiDevice uiDevice) {
        super(uiDevice);
    }

    @Override
    public RawUiElementUnion findByResourceId(String resourceId) {
        UiObject2 control = uiDevice.findObject(By.res(resourceId));
        return new RawUiElementUnion(control);
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
