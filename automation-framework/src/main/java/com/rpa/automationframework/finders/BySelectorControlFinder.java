package com.rpa.automationframework.finders;

import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject2;


import com.rpa.automationframework.internal.types.RawUiElementUnion;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Finds UiObject2 controls using the BySelector class.
 */
public class BySelectorControlFinder extends UiAutomatorControlFinder {

    public BySelectorControlFinder(UiDevice uiDevice) {
        super(uiDevice);
    }

    /**
     * Finds a control by its resource id.
     *
     * @param resourceId The resource id of the control.
     * @return The control with matching resource id.
     */
    @Override
    public RawUiElementUnion findByResourceId(String resourceId) {
        UiObject2 control = uiDevice.findObject(By.res(resourceId));
        return new RawUiElementUnion(control);
    }

    /**
     * Finds controls by their description.
     *
     * @param description The description of the controls.
     * @return The controls with matching description.
     */
    @Override
    public List<RawUiElementUnion> findByDescription(String description) {
        return uiDevice.findObjects(By.desc(description)).stream().map(RawUiElementUnion::new).collect(Collectors.toList());
    }

    /**
     * Finds controls by their text content.
     *
     * @param content The text content of the controls.
     * @return The controls with matching text content.
     */
    @Override
    public List<RawUiElementUnion> findByTextContent(String content) {
        return uiDevice.findObjects(By.text(content)).stream().map(RawUiElementUnion::new).collect(Collectors.toList());
    }
}
