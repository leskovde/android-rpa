package com.rpa.automationframework.executors;

import com.rpa.automationframework.controls.TextBasedUiElement;
import com.rpa.automationframework.controls.UiElement;

import kotlin.NotImplementedError;

public interface UiActionExecutor {
    Class<?> getInternalType(UiElement element);

    String getText(TextBasedUiElement element);

    boolean isChecked(TextBasedUiElement element);

    void setCheckedValue(TextBasedUiElement element, boolean checked);

    void click(UiElement element);

    void longClick(UiElement element);
}
