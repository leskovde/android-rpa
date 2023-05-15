package com.rpa.wawinterpreter.waw.selectors;

import com.rpa.automationframework.controls.UiElement;

/**
 * Represents a selector for matching the current state of the device.
 * <p>
 * Selectors are mapped to ways of identifying UI elements on the device, such as
 * by id, text, or index.
 * <p>
 * The type of the selector (e.g. button, textview) is required. Specific properties
 * then have to be provided depending on the type of the selector.
 */
public abstract class Selector {
    protected TypeNames type;

    /**
     * Attempts to find an UI element that matches the provided type and arguments.
     *
     * @return The matching UI element if found, otherwise null.
     */
    public abstract UiElement getUiElement();
}
