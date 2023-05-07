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
        for (ControlFinder finder : Device.getInstance().controlFinders) {
            List<RawUiElementUnion> controls = finder.findByTextContent(resourceId);

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

        // TODO: Move the type check up so that we match the proper ui element.
        if (!isValidType()) {
            Log.println(Log.WARN, "UiElement", "Defaulting to UiElement when searching for " + this.getClass().getSimpleName());
        }

        return state != RawUiElementState.NONE;
    }
}
