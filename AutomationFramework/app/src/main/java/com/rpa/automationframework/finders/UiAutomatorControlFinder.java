package com.rpa.automationframework.finders;

import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiSelector;

import com.rpa.automationframework.internal.types.RawUiElementUnion;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<RawUiElementUnion> findByClassName(String className) {
        return uiDevice.findObjects(By.clazz(className)).stream().map(RawUiElementUnion::new).collect(Collectors.toList());
    }
}
