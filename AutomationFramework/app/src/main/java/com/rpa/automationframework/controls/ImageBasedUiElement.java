package com.rpa.automationframework.controls;

import android.graphics.Bitmap;

import com.rpa.automationframework.executors.UiActionExecutor;

public abstract class ImageBasedUiElement extends UiElement {
    public Bitmap getImage() {
        UiActionExecutor executor = executors.get(state);

        if (executor != null) {
            return executor.getImage(this);
        }

        return null;
    }
}
