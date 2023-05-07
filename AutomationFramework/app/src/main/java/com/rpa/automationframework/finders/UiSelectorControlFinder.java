package com.rpa.automationframework.finders;

import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiSelector;

import com.rpa.automationframework.internal.types.RawUiElementUnion;

import java.util.ArrayList;
import java.util.List;

// TODO: Check that the returned control exists before returning it.
public class UiSelectorControlFinder extends UiAutomatorControlFinder {

    public UiSelectorControlFinder(UiDevice uiDevice) {
        super(uiDevice);
    }

    @Override
    public RawUiElementUnion findByResourceId(String resourceId) {
        UiObject control = uiDevice.findObject(new UiSelector().resourceId(resourceId));
        return new RawUiElementUnion(control);
    }

    @Override
    public RawUiElementUnion findByDescription(String description) {
        return null;
    }

    @Override
    public List<RawUiElementUnion> findByTextContent(String content) {
        List<RawUiElementUnion> controls = new ArrayList<>();
        UiObject control = uiDevice.findObject(new UiSelector().text(content));
        controls.add(new RawUiElementUnion(control));

        return controls;
    }
}
