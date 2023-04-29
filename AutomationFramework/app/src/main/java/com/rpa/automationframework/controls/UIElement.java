package com.rpa.automationframework.controls;

import android.graphics.Rect;

import androidx.test.uiautomator.*;

public abstract class UIElement {
    private UiObject uiObject;
    private UiObject2 uiObject2;
    private boolean found = false;

    public int index;
    public String resourceId;
    public String className;
    public String packageName;
    public String description;
}
