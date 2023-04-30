package com.rpa.automationframework.controls;

import androidx.test.uiautomator.*;

import com.rpa.automationframework.Device;
import com.rpa.automationframework.executors.UiActionExecutor;
import com.rpa.automationframework.finders.ControlFinder;
import com.rpa.automationframework.internal.types.RawUiElementState;

import java.util.HashMap;
import java.util.Map;

public abstract class UiElement {
    private UiObject uiObject;
    private UiObject2 uiObject2;
    private RawUiElementState state = RawUiElementState.NONE;
    // TODO: Register all executors using reflection?
    private static Map<RawUiElementState, UiActionExecutor> executors = new HashMap<>();

    public int index;
    public String resourceId;
    public String className;
    public String packageName;
    public String description;

    public boolean tryFindByIndex() {
        for (ControlFinder finder : Device.getInstance().controlFinders) {
            Object element = finder.findByControlIndex(index).getPayload();

            if (element != null) {
                if (element instanceof UiObject) {
                    uiObject = (UiObject) element;
                    state = RawUiElementState.UIOBJECT;
                } else {
                    uiObject2 = (UiObject2) element;
                    state = RawUiElementState.UIOBJECT2;
                }
                break;
            }
        }

        return state != RawUiElementState.NONE;
    }

    public void click() {
        executors.get(state).click();
    }
}
