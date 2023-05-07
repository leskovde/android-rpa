package com.rpa.automationframework.executors;

import androidx.test.uiautomator.UiObject2;

import com.rpa.automationframework.controls.TextBasedUiElement;
import com.rpa.automationframework.controls.UiElement;

// TODO: I should check whether the incoming UiObject2 exists.
public class UiObject2ActionExecutor implements UiActionExecutor {

    @Override
    public Class<?> getInternalType(UiElement element) {
        return element.getUiObject2Type();
    }

    @Override
    public String getText(TextBasedUiElement element) {
        UiObject2 control = element.getUiObject2();
        return control.getText();
    }

    @Override
    public boolean isChecked(TextBasedUiElement element) {
        UiObject2 control = element.getUiObject2();
        return control.isChecked();
    }
}
