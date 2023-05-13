package com.rpa.automationframework.controls;

public class ImageView extends ImageBasedUiElement {

    @Override
    protected boolean isInternalTypeAssignable(String internalType) {
        return internalType.toLowerCase().contains("image");
    }
}
