package com.rpa.automationframework.finders;

import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObject2;
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

    @Override
    public RawUiElementUnion findByPosition(int x, int y) {
        List<UiObject2> controls = uiDevice.findObjects(By.clickable(true));

        // TODO: Revise this logic to find the control that is closest to the position, not some
        //  container that spans the entire screen.
        for (UiObject2 control : controls) {
            if(control.getVisibleBounds().contains(x, y)) {
                return new RawUiElementUnion(control);
            }
        }

        throw new RuntimeException("Unable to find control at position (" + x + ", " + y + ")");
    }
}
