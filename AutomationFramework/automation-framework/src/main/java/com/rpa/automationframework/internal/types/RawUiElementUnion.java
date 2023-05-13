package com.rpa.automationframework.internal.types;

import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObject2;

public class RawUiElementUnion {
    private UiObject uiObject;
    private UiObject2 uiObject2;

    private RawUiElementState state = RawUiElementState.NONE;

    public RawUiElementUnion(UiObject uiObject) {

        this.uiObject = uiObject;
        this.state = RawUiElementState.UIOBJECT;
    }

    public RawUiElementUnion(UiObject2 uiObject2) {

        this.uiObject2 = uiObject2;
        this.state = RawUiElementState.UIOBJECT2;
    }

    public Object getPayload() {
        if (state == RawUiElementState.UIOBJECT) {
            return uiObject;
        } else if (state == RawUiElementState.UIOBJECT2) {
            return uiObject2;
        } else {
            return null;
        }
    }

    public RawUiElementState getState() {
        return state;
    }
}
