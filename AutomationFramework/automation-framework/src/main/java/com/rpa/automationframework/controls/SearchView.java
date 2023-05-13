package com.rpa.automationframework.controls;

public class SearchView extends TextBasedUiElement {

    @Override
    protected boolean isInternalTypeAssignable(String internalType) {
        return internalType.toLowerCase().contains("searchview");
    }
}
