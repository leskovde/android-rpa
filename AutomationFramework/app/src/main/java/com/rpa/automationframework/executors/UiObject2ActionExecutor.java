package com.rpa.automationframework.executors;

import com.rpa.automationframework.controls.UiElement;

public class UiObject2ActionExecutor implements UiActionExecutor {

    @Override
    public Class<?> getInternalType(UiElement element) {
        return element.getUiObject2Type();
    }
}
