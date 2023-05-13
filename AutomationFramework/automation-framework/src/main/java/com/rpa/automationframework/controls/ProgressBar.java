package com.rpa.automationframework.controls;

public class ProgressBar extends UiElement {

    @Override
    protected boolean isInternalTypeAssignable(String internalType) {
        if (internalType.toLowerCase().contains("progressbar"))
            return true;

        if (internalType.toLowerCase().contains("seekbar"))
            return true;

        if (internalType.toLowerCase().contains("ratingbar"))
            return true;

        return false;
    }
}
