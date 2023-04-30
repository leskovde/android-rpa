package com.rpa.automationframework.internal.types;

import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObject2;

public class RawUiElementUnion {
    private UiObject uiObject;
    private UiObject2 uiObject2;

    public RawUiElementUnion(UiObject uiObject) {
        this.uiObject = uiObject;
    }

    public RawUiElementUnion(UiObject2 uiObject2) {
        this.uiObject2 = uiObject2;
    }

    public Object getPayload() {
        if (uiObject != null) {
            return uiObject;
        } else {
            return uiObject2;
        }
    }
}
