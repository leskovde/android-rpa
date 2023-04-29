package com.rpa.automationframework;

import com.rpa.automationframework.controls.UIElement;

public interface ControlFinder {
    public UIElement findByControlIndex(int index);

    public  UIElement findByResourceId(String resourceId);

    // TODO: Use an enum?
    public UIElement findByClassName(String className);

    public UIElement findByDescription(String description);
}
