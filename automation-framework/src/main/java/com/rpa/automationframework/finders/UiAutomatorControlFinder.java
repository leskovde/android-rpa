package com.rpa.automationframework.finders;

import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.UiSelector;

import com.rpa.automationframework.internal.types.RawUiElementUnion;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Finds either UiObject or UiObject2 controls.
 * <p>
 * Implements the logic for finding controls using the UiAutomator framework
 * when there is only a single way of finding the control.
 */
public abstract class UiAutomatorControlFinder implements ControlFinder {
    protected UiDevice uiDevice;

    public UiAutomatorControlFinder(UiDevice uiDevice) {
        this.uiDevice = uiDevice;
    }

    /**
     * Finds a control by its index - the order in which it appears in the layout hierarchy.
     *
     * @param index The index of the control.
     * @return The UiObject-based control with matching index.
     */
    @Override
    public RawUiElementUnion findByControlIndex(int index) {
        uiDevice.waitForIdle();
        UiObject control = uiDevice.findObject(new UiSelector().index(index));
        return new RawUiElementUnion(control);
    }

    /**
     * Finds controls by their class name, i.e. the type of control they are.
     * <p>
     * E.g., "android.widget.Button" for a button.
     *
     * @param className The class name of the control.
     * @return UiObject2-based controls with matching class name.
     */
    @Override
    public List<RawUiElementUnion> findByClassName(String className) {
        uiDevice.waitForIdle();
        return uiDevice.findObjects(By.clazz(className)).stream().map(RawUiElementUnion::new).collect(Collectors.toList());
    }

    /**
     * Finds a control by its position on the screen.
     * <p>
     * The controls considered are those that are clickable.
     *
     * @param x The x coordinate of the control.
     * @param y The y coordinate of the control.
     * @return The UiObject2-based control with matching resource id.
     */
    @Override
    public RawUiElementUnion findByPosition(int x, int y) {
        uiDevice.waitForIdle();
        List<UiObject2> controls = uiDevice.findObjects(By.clickable(true));

        // TODO: Revise this logic to find the control that is closest to the position, not some
        //  container that spans the entire screen.
        for (UiObject2 control : controls) {
            if (control.getVisibleBounds().contains(x, y)) {
                return new RawUiElementUnion(control);
            }
        }

        throw new RuntimeException("Unable to find control at position (" + x + ", " + y + ")");
    }
}
