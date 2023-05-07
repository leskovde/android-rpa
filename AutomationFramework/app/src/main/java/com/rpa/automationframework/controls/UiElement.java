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
import java.util.function.Consumer;

public abstract class UiElement {
    protected static Map<RawUiElementState, UiActionExecutor> executors;

    static {
        executors = new HashMap<>();
        // TODO: Add all executors here.
        // Maybe use reflection to register them?
    }

    protected UiObject uiObject;
    protected UiObject2 uiObject2;
    protected RawUiElementState state = RawUiElementState.NONE;
    // TODO: Register all executors using reflection?
    protected Map<RawUiElementState, InternalObjectAssigner> assigners;

    public int index;
    public String resourceId;
    public String className;
    public String packageName;
    public String description;

    public UiElement() {
        assigners = new HashMap<>();
        assigners.put(RawUiElementState.UIOBJECT, new UiObjectAssigner());
        assigners.put(RawUiElementState.UIOBJECT2, new UiObject2Assigner());
    }

    public abstract boolean isInternalTypeAssignable(Class<?> internalType);

    /**
     * Tries to find the element by index.
     * <p>
     * There is only one element with a given index, so if we find one, we can stop.
     *
     * @return True if the element was found, false otherwise.
     */
    public boolean tryFindByIndex() {
        RawUiElementUnion lastValidElement = null;

        for (ControlFinder finder : Device.getInstance().controlFinders) {
            RawUiElementUnion control = finder.findByControlIndex(index);
            InternalObjectAssigner assigner = assigners.get(control.getState());
            if (assigner == null) {
                continue;
            }

            InternalObjectAssigner.AssignmentResult assignmentResult = assigner.tryAssign(control);
            if (assignmentResult == InternalObjectAssigner.AssignmentResult.MATCHING) {
                // We have found the best candidate, so we can stop.
                return true;
            }

            if (assignmentResult == InternalObjectAssigner.AssignmentResult.FALLBACK) {
                // The element does not have the valid type, but we can still use it.
                lastValidElement = control;
            }
        }

        if (lastValidElement != null) {
            InternalObjectAssigner assigner = assigners.get(lastValidElement.getState());
            if (assigner != null) {
                assigner.tryAssign(lastValidElement);
            }

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
     *
     * @param className The type of the element, e.g. "android.widget.Button".
     * @return True if the element was found, false otherwise.
     */
    public boolean tryFindByClassName(String className) {
        RawUiElementUnion lastValidElement = null;
        List<String> possibleNames = NameUtils.getClassNames(className);

        for (String name : possibleNames) {
            for (ControlFinder finder : Device.getInstance().controlFinders) {
                List<RawUiElementUnion> controls = finder.findByClassName(name);

                for (RawUiElementUnion control : controls) {
                    InternalObjectAssigner assigner = assigners.get(control.getState());
                    if (assigner == null) {
                        continue;
                    }

                    InternalObjectAssigner.AssignmentResult assignmentResult = assigner.tryAssign(control);
                    if (assignmentResult == InternalObjectAssigner.AssignmentResult.MATCHING) {
                        // We have found the best candidate, so we can stop.
                        return true;
                    }

                    if (assignmentResult == InternalObjectAssigner.AssignmentResult.FALLBACK) {
                        // The element does not have the valid type, but we can still use it.
                        lastValidElement = control;
                    }
                }
            }
        }

        if (lastValidElement != null) {
            InternalObjectAssigner assigner = assigners.get(lastValidElement.getState());
            if (assigner != null) {
                assigner.tryAssign(lastValidElement);
            }

            Log.println(Log.WARN, "UiElement", "Defaulting to UiElement when searching for " + this.getClass().getSimpleName());
        }

        return state != RawUiElementState.NONE;
    }

    public boolean tryFindByResourceId(String resourceId) {
        RawUiElementUnion lastValidElement = null;

        for (ControlFinder finder : Device.getInstance().controlFinders) {
            RawUiElementUnion control = finder.findByResourceId(resourceId);
            InternalObjectAssigner assigner = assigners.get(control.getState());
            if (assigner == null) {
                continue;
            }

            InternalObjectAssigner.AssignmentResult assignmentResult = assigner.tryAssign(control);
            if (assignmentResult == InternalObjectAssigner.AssignmentResult.MATCHING) {
                // We have found the best candidate, so we can stop.
                return true;
            }

            if (assignmentResult == InternalObjectAssigner.AssignmentResult.FALLBACK) {
                // The element does not have the valid type, but we can still use it.
                lastValidElement = control;
            }
        }

        if (lastValidElement != null) {
            InternalObjectAssigner assigner = assigners.get(lastValidElement.getState());
            if (assigner != null) {
                assigner.tryAssign(lastValidElement);
            }

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
        if (uiObject != null) {
            return uiObject.getClass();
        }

        return null;
    }

    public Class<?> getUiObject2Type() {
        if (uiObject2 != null) {
            return uiObject2.getClass();
        }

        return null;
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

    protected interface InternalObjectAssigner {
        enum AssignmentResult {
            MATCHING, FALLBACK, FAILED
        }

        AssignmentResult tryAssign(RawUiElementUnion element);
    }

    private class UiObjectAssigner implements InternalObjectAssigner {
        @Override
        public AssignmentResult tryAssign(RawUiElementUnion element) {
            Object payload = element.getPayload();

            if (payload instanceof UiObject) {
                uiObject = (UiObject) payload;
                state = RawUiElementState.UIOBJECT;

                if (isValidType()) {
                    return AssignmentResult.MATCHING;
                } else {
                    return AssignmentResult.FALLBACK;
                }
            }

            return AssignmentResult.FAILED;
        }
    }

    private class UiObject2Assigner implements InternalObjectAssigner {
        @Override
        public AssignmentResult tryAssign(RawUiElementUnion element) {
            Object payload = element.getPayload();

            if (payload instanceof UiObject2) {
                uiObject2 = (UiObject2) payload;
                state = RawUiElementState.UIOBJECT2;

                if (isValidType()) {
                    return AssignmentResult.MATCHING;
                } else {
                    return AssignmentResult.FALLBACK;
                }
            }

            return AssignmentResult.FAILED;
        }
    }
}
