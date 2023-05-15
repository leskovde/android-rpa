package com.rpa.automationframework.controls;

import android.graphics.Bitmap;

import com.rpa.automationframework.executors.UiActionExecutor;

/**
 * Represents the root of image-based UI control hierarchy.
 */
public abstract class ImageBasedUiElement extends UiElement {

    /**
     * Gets the image of the UI element.
     * <p>
     * This is currently done by taking a screenshot of the device and cropping the image
     * to the bounds of the UI element.
     *
     * @return The image of the UI element as a bitmap.
     */
    public Bitmap getImage() {
        UiActionExecutor executor = executors.get(state);

        if (executor != null) {
            return executor.getImage(this);
        }

        return null;
    }
}
