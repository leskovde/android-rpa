package com.rpa.automationframework.executors;

import android.util.Log;

import androidx.test.uiautomator.UiObject;

import com.rpa.automationframework.controls.TextBasedUiElement;
import com.rpa.automationframework.controls.UiElement;

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
}
