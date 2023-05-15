package com.rpa.automationframework.controls;

import android.util.Log;

import com.rpa.automationframework.Device;
import com.rpa.automationframework.executors.UiActionExecutor;
import com.rpa.automationframework.finders.ControlFinder;
import com.rpa.automationframework.internal.types.RawUiElementState;
import com.rpa.automationframework.internal.types.RawUiElementUnion;

import java.util.List;

/**
 * Represents the root of text-based UI control hierarchy.
 * <p>
 * These are the controls that have text content and allow
 * us to extract the content.
 */
public abstract class TextBasedUiElement extends UiElement {

    /**
     * Tries to find the element by its text content.
     * <p>
     * There could be many elements with the same text content, so we need to
     * find the best candidate. The best candidate is the one that has the
     * most specific type (e.g. Button when searching for a button). If there
     * is no such element, we fall back to the default type.
     *
     * @return True if the element was found, false otherwise.
     */
    public boolean tryFindByContent(String content) {
        RawUiElementUnion lastValidElement = null;

        for (ControlFinder finder : Device.getInstance().controlFinders) {
            List<RawUiElementUnion> controls = finder.findByTextContent(content);

            for (RawUiElementUnion control : controls) {
                InternalObjectAssigner assigner = assigners.get(control.getState());
                if (assigner == null) {
                    continue;
                }

                InternalObjectAssigner.AssignmentResult assignmentResult = assigner.tryAssign(control);
                if (assignmentResult.equals(InternalObjectAssigner.AssignmentResult.MATCHING)) {
                    // We have found the best candidate, so we can stop.
                    return true;
                }

                if (assignmentResult.equals(InternalObjectAssigner.AssignmentResult.FALLBACK)) {
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

    /**
     * Gets the text of the UI element.
     *
     * @return The text of the UI element.
     */
    public String getText() {
        UiActionExecutor executor = executors.get(state);

        if (executor != null) {
            return executor.getText(this);
        }

        return "";
    }
}
