package com.rpa.automationframework.finders;

import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiSelector;

import com.rpa.automationframework.internal.types.RawUiElementUnion;

import java.util.ArrayList;
import java.util.List;

/**
 * Finds UiObject controls using the UiSelector class.
 */
public class UiSelectorControlFinder extends UiAutomatorControlFinder {

    public UiSelectorControlFinder(UiDevice uiDevice) {
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
        uiDevice.waitForIdle();
        UiObject control = uiDevice.findObject(new UiSelector().resourceId(resourceId));
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
        uiDevice.waitForIdle();
        List<RawUiElementUnion> controls = new ArrayList<>();
        UiObject control = uiDevice.findObject(new UiSelector().description(description));
        controls.add(new RawUiElementUnion(control));

        return controls;
    }

    /**
     * Finds controls by their text content.
     *
     * @param content The text content of the controls.
     * @return The controls with matching text content.
     */
    @Override
    public List<RawUiElementUnion> findByTextContent(String content) {
        uiDevice.waitForIdle();
        List<RawUiElementUnion> controls = new ArrayList<>();
        UiObject control = uiDevice.findObject(new UiSelector().text(content));
        controls.add(new RawUiElementUnion(control));

        return controls;
    }
}
