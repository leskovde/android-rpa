package com.rpa.automationframework.controls;

/**
 * Represents a clickable UI element.
 * <p>
 * Since some of the default android UI elements that serve as buttons
 * are not of type "Button" (e.g. TextView), this class also represents
 * those types.
 */
public class Button extends TextBasedUiElement {

    @Override
    protected boolean isInternalTypeAssignable(String internalType) {
        if (internalType.toLowerCase().contains("textview")) return true;

        if (internalType.toLowerCase().contains("button")) return true;

        if (internalType.toLowerCase().contains("checkbox")) return true;

        return internalType.toLowerCase().contains("switch");
    }
}
