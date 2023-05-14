package com.rpa.wawinterpreter.waw.selectors;

import com.rpa.automationframework.controls.UiElement;
import com.rpa.wawinterpreter.waw.factories.UiElementFactory;

public class ResIdSelector extends Selector {
    private final String resId;

    public ResIdSelector(String resId, TypeNames type) {
        this.type = type;
        this.resId = resId;
    }

    @Override
    public UiElement getUiElement() {
        UiElement element = UiElementFactory.getUiElement(this.type);
        if (!element.tryFindByResourceId(resId)) {
            return null;
        }

        return element;
    }
}
