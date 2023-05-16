package com.rpa.automationframework.controls;

/**
 * Represents a view - an element that displays content.
 * <p>
 * View tends to be the most generic element type and serves mainly
 * for identifying the root element of a screen.
 */
public class View extends UiElement {

    @Override
    protected boolean isInternalTypeAssignable(String internalType) {
        return internalType.toLowerCase().contains("view");
    }
}
