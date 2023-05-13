package com.rpa.automationframework.controls;

public class TextView extends TextBasedUiElement {

    @Override
    protected boolean isInternalTypeAssignable(String internalType) {
        if (internalType.toLowerCase().contains("textview"))
            return true;

        if (internalType.toLowerCase().contains("button"))
            return true;

        if (internalType.toLowerCase().contains("chronometer"))
            return true;

        if (internalType.toLowerCase().contains("clock"))
            return true;

        if (internalType.toLowerCase().contains("checkbox"))
            return true;

        if (internalType.toLowerCase().contains("switch"))
            return true;

        return false;
    }
}
