package com.rpa.automationframework.controls;

import com.rpa.automationframework.executors.UiActionExecutor;

/**
 * Represents an editable text field.
 * <p>
 * Matches to EditText or to any AutoComplete control.
 */
public class TextEdit extends TextBasedUiElement {

    /**
     * Sets the text content of the UI element.
     *
     * @param text the text to be set.
     */
    public void setText(String text) {
        UiActionExecutor executor = executors.get(state);

        if (executor == null) {
            throw new RuntimeException("No executor found for state: " + state);
        }

        executor.setText(this, text);
    }

    @Override
    protected boolean isInternalTypeAssignable(String internalType) {
        if (internalType.toLowerCase().contains("edittext")) return true;

        return internalType.toLowerCase().contains("autocomplete");
    }
}
