package com.rpa.automationframework.controls;

import com.rpa.automationframework.executors.UiActionExecutor;

public class TextEdit extends TextBasedUiElement {

    @Override
    protected boolean isInternalTypeAssignable(String internalType) {
        if (internalType.toLowerCase().contains("edittext"))
            return true;

        return internalType.toLowerCase().contains("autocomplete");
    }

    public void setText(String text) {
        UiActionExecutor executor = executors.get(state);

        if (executor == null) {
            throw new RuntimeException("No executor found for state: " + state);
        }

        executor.setText(this, text);
    }
}
