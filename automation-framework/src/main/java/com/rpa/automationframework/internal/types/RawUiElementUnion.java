package com.rpa.automationframework.internal.types;

import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObject2;

/**
 * Represents a union of UiObject and UiObject2 (or possibly other types in the future).
 */
public class RawUiElementUnion {
    private UiObject uiObject;
    private UiObject2 uiObject2;

    private RawUiElementState state;

    public RawUiElementUnion(UiObject uiObject) {

        this.uiObject = uiObject;
        this.state = RawUiElementState.UIOBJECT;
    }

    public RawUiElementUnion(UiObject2 uiObject2) {

        this.uiObject2 = uiObject2;
        this.state = RawUiElementState.UIOBJECT2;
    }

    /**
     * Gets the underlying UiObject.
     *
     * @return UiObject or UiObject2, based on what was provided in the constructor. Otherwise null.
     */
    public Object getPayload() {
        if (state == RawUiElementState.UIOBJECT) {
            return uiObject;
        } else if (state.equals(RawUiElementState.UIOBJECT2)) {
            return uiObject2;
        } else {
            return null;
        }
    }

    /**
     * Gets the state of the underlying UI object representation.
     *
     * @return The name of the underlying UI object representation.
     */
    public RawUiElementState getState() {
        return state;
    }
}
