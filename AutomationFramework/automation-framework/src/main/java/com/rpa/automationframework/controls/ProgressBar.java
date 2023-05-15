package com.rpa.automationframework.controls;

/**
 * Represents a progress bar.
 * <p>
 * There are no methods unique to this control. Use methods inherited from {@link UiElement}.
 */
public class ProgressBar extends UiElement {

    @Override
    protected boolean isInternalTypeAssignable(String internalType) {
        if (internalType.toLowerCase().contains("progressbar")) return true;

        if (internalType.toLowerCase().contains("seekbar")) return true;

        return internalType.toLowerCase().contains("ratingbar");
    }
}
