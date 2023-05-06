package com.rpa.automationframework.executors;

import com.rpa.automationframework.controls.UiElement;

public class UiObjectActionExecutor implements UiActionExecutor {

    @Override
    public Class<?> getInternalType(UiElement element) {
        return element.getUiObjectType();
    }
}
