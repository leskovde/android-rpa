package com.rpa.automationframework.executors;

import android.graphics.Bitmap;

import com.rpa.automationframework.controls.TextBasedUiElement;
import com.rpa.automationframework.controls.UiElement;

public interface UiActionExecutor {
    String getInternalType(UiElement element);

    String getText(TextBasedUiElement element);

    boolean isChecked(TextBasedUiElement element);

    void setCheckedValue(TextBasedUiElement element, boolean checked);

    void click(UiElement element);

    void longClick(UiElement element);

    void setText(TextBasedUiElement element, String text);

    Bitmap getImage(UiElement element);
}
