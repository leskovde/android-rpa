package com.rpa.wawinterpreter.waw.selectors;

import com.rpa.automationframework.controls.UiElement;
import com.rpa.wawinterpreter.waw.factories.UiElementFactory;

public class ClassNameSelector extends Selector {
    private final String className;

    public ClassNameSelector(String className, TypeNames type) {
        this.type = type;
        this.className = className;
    }

    @Override
    public UiElement getUiElement() {
        UiElement element = UiElementFactory.getUiElement(this.type);
        if (!element.tryFindByClassName(className)) {
            return null;
        }

        return element;
    }
}
