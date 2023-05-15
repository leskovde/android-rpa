package com.rpa.automationframework.controls;

/**
 * Represents an image view - an element that displays an image
 * from a resource.
 * <p>
 * Matches to any UI element whose internal type contains the word "image",
 * e.g. ImageView, ImageButton, etc.
 */
public class ImageView extends ImageBasedUiElement {

    @Override
    protected boolean isInternalTypeAssignable(String internalType) {
        return internalType.toLowerCase().contains("image");
    }
}
