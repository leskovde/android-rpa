package com.rpa.automationframework.controls;

import android.util.Log;

import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObject2;

import com.rpa.automationframework.Device;
import com.rpa.automationframework.finders.ControlFinder;
import com.rpa.automationframework.internal.types.RawUiElementState;
import com.rpa.automationframework.internal.types.RawUiElementUnion;

import java.util.List;

public abstract class TextBasedUiElement extends UiElement {
    public String text;

    public boolean tryFindByContent() {
        RawUiElementUnion lastValidElement = null;

        for (ControlFinder finder : Device.getInstance().controlFinders) {
            List<RawUiElementUnion> controls = finder.findByTextContent(resourceId);

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

        if (lastValidElement != null) {
            InternalObjectAssigner assigner = assigners.get(lastValidElement.getState());
            if (assigner != null) {
                assigner.tryAssign(lastValidElement);
            }

            Log.println(Log.WARN, "UiElement", "Defaulting to UiElement when searching for " + this.getClass().getSimpleName());
        }

        return state != RawUiElementState.NONE;
    }
}
