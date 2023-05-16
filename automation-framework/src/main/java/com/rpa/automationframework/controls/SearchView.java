package com.rpa.automationframework.controls;

import com.rpa.automationframework.executors.UiActionExecutor;

/**
 * Represents a search view.
 * <p>
 * Allows to extract or set the text of the search view.
 */
public class SearchView extends TextBasedUiElement {

    /**
     * Sets the text of the search view.
     *
     * @param text the text to be searched.
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
        return internalType.toLowerCase().contains("searchview");
    }
}
