package com.rpa.automationframework.executors;

import kotlin.NotImplementedError;

public interface UiActionExecutor {
    default void click() {
        throw new NotImplementedError();
    }
}
