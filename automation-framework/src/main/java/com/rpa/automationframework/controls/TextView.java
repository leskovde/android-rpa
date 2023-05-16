package com.rpa.automationframework.controls;

/**
 * Represents a readable text view.
 * <p>
 * Matches UI elements that display text.
 */
public class TextView extends TextBasedUiElement {

    @Override
    protected boolean isInternalTypeAssignable(String internalType) {
        if (internalType.toLowerCase().contains("textview")) return true;

        if (internalType.toLowerCase().contains("button")) return true;

        if (internalType.toLowerCase().contains("chronometer")) return true;

        if (internalType.toLowerCase().contains("clock")) return true;

        if (internalType.toLowerCase().contains("checkbox")) return true;

        return internalType.toLowerCase().contains("switch");
    }
}
