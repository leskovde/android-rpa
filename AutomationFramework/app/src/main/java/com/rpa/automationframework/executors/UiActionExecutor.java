package com.rpa.automationframework.executors;

import com.rpa.automationframework.controls.UiElement;

import kotlin.NotImplementedError;

public interface UiActionExecutor {
    default void click(UiElement element) {
        throw new NotImplementedError();
    }
}
