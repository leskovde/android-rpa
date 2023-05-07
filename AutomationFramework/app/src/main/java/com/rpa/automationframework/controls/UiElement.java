package com.rpa.automationframework.controls;

import android.util.Log;

import androidx.test.uiautomator.*;

import com.rpa.automationframework.Device;
import com.rpa.automationframework.executors.UiActionExecutor;
import com.rpa.automationframework.finders.ControlFinder;
import com.rpa.automationframework.internal.helper.NameUtils;
import com.rpa.automationframework.internal.types.RawUiElementState;
import com.rpa.automationframework.internal.types.RawUiElementUnion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class UiElement {
    protected UiObject uiObject;
    protected UiObject2 uiObject2;
    protected RawUiElementState state = RawUiElementState.NONE;
    // TODO: Register all executors using reflection?
    protected static Map<RawUiElementState, UiActionExecutor> executors = new HashMap<>();

    public int index;
    public String resourceId;
    public String className;
    public String packageName;
    public String description;

    public abstract boolean isInternalTypeAssignable(Class<?> internalType);

    /**
     * Tries to find the element by index.
     * <p>
     * There is only one element with a given index, so if we find one, we can stop.
     * @return True if the element was found, false otherwise.
     */
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

        if (!isValidType()) {
            Log.println(Log.WARN, "UiElement", "Defaulting to UiElement when searching for " + this.getClass().getSimpleName());
        }

        return state != RawUiElementState.NONE;
    }

    /**
     * Tries to find the element by the provided className.
     * <p>
     * Each UI element has a class property, which is a string that represents the type of the element.
     * We try to find the element by the provided className. If we find one, we can stop.
     * Otherwise, we try to find the element by parts of the className.
     * @param className The type of the element, e.g. "android.widget.Button".
     * @return True if the element was found, false otherwise.
     */
    public boolean tryFindByClassName(String className) {
        List<String> possibleNames = NameUtils.getClassNames(className);

        for (String name : possibleNames) {
            for (ControlFinder finder : Device.getInstance().controlFinders) {
                List<RawUiElementUnion> controls = finder.findByClassName(name);

                for (RawUiElementUnion control : controls) {
                    Object element = control.getPayload();

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
            }
        }

        // TODO: Move the type check up so that we match the proper ui element.
        if (!isValidType()) {
            Log.println(Log.WARN, "UiElement", "Defaulting to UiElement when searching for " + this.getClass().getSimpleName());
        }

        return state != RawUiElementState.NONE;
    }

    public boolean tryFindByResourceId(String resourceId) {
        for (ControlFinder finder : Device.getInstance().controlFinders) {
            Object element = finder.findByResourceId(resourceId).getPayload();

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

        if (!isValidType()) {
            Log.println(Log.WARN, "UiElement", "Defaulting to UiElement when searching for " + this.getClass().getSimpleName());
        }

        return state != RawUiElementState.NONE;
    }

    public void click() {
        UiActionExecutor executor = executors.get(state);

        if (executor == null) {
            throw new RuntimeException("No executor found for state: " + state);
        }

        executor.click(this);
    }

    public Class<?> getUiObjectType() {
        return uiObject.getClass();
    }

    public Class<?> getUiObject2Type() {
        return uiObject2.getClass();
    }

    protected boolean isValidType() {
        UiActionExecutor executor = executors.get(state);

        if (executor == null) {
            throw new RuntimeException("No executor found for state: " + state);
        }

        Class<?> internalType = executor.getInternalType(this);

        if (internalType == null) {
            throw new RuntimeException("No internal type found for state: " + state);
        }

        return isInternalTypeAssignable(internalType);
    }
}
