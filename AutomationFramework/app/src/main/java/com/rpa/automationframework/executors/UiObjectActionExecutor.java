package com.rpa.automationframework.executors;

import android.util.Log;

import androidx.test.uiautomator.UiObject;

import com.rpa.automationframework.controls.TextBasedUiElement;
import com.rpa.automationframework.controls.UiElement;

// TODO: I should check whether the incoming UiObject exists.
public class UiObjectActionExecutor implements UiActionExecutor {

    @Override
    public Class<?> getInternalType(UiElement element) {
        return element.getUiObjectType();
    }

    @Override
    public String getText(TextBasedUiElement element) {
        UiObject control = element.getUiObject();
        String text = "";

        try {
            control.getText();
        } catch (Exception e) {
            Log.println(Log.ERROR, "UiObjectActionExecutor", "Error getting text from UiObject");
        }

        return text;
    }

    @Override
    public boolean isChecked(TextBasedUiElement element) {
        UiObject control = element.getUiObject();

        try {
            return control.isChecked();
        } catch (Exception e) {
            Log.println(Log.ERROR, "UiObjectActionExecutor", "Error getting checked state from UiObject");
        }

        return false;
    }

    @Override
    public void setCheckedValue(TextBasedUiElement element, boolean checked) {
        UiObject control = element.getUiObject();

        try {
            if (!control.isCheckable())
                return;

            // TODO: Is this the best way to do this?
            if (control.isChecked() != checked)
                control.click();
        } catch (Exception e) {
            Log.println(Log.ERROR, "UiObjectActionExecutor", "Error setting checked state on UiObject");
        }
    }
}
