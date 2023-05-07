package com.rpa.automationframework.finders;

import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObject2;


import com.rpa.automationframework.internal.types.RawUiElementUnion;

import java.util.List;
import java.util.stream.Collectors;

// TODO: Check that the returned control exists before returning it.
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
    public List<RawUiElementUnion> findByDescription(String description) {
        return uiDevice.findObjects(By.desc(description)).stream().map(RawUiElementUnion::new).collect(Collectors.toList());
    }

    @Override
    public List<RawUiElementUnion> findByTextContent(String content) {
        return uiDevice.findObjects(By.text(content)).stream().map(RawUiElementUnion::new).collect(Collectors.toList());
    }
}
