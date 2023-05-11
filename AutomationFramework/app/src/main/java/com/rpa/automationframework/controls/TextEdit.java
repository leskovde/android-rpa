package com.rpa.automationframework.controls;

public class TextEdit extends TextBasedUiElement {

    @Override
    protected boolean isInternalTypeAssignable(String internalType) {
        if (internalType.toLowerCase().contains("edittext"))
            return true;

        if (internalType.toLowerCase().contains("autocomplete"))
            return true;

        return false;
    }
}
