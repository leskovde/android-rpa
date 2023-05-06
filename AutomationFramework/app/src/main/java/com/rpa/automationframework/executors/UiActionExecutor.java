package com.rpa.automationframework.executors;

import com.rpa.automationframework.controls.UiElement;

import kotlin.NotImplementedError;

// TODO: Remove default.
public interface UiActionExecutor {
    default void click(UiElement element) {
        throw new NotImplementedError();
    }

    Class<?> getInternalType(UiElement element);
}
