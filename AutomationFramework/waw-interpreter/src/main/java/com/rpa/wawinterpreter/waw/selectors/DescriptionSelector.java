package com.rpa.wawinterpreter.waw.selectors;

import com.rpa.automationframework.controls.UiElement;
import com.rpa.wawinterpreter.waw.factories.UiElementFactory;

public class DescriptionSelector extends Selector {
    private final String description;

    public DescriptionSelector(String description, TypeNames type) {
        this.type = type;
        this.description = description;
    }

    @Override
    public UiElement getUiElement() {
        UiElement element = UiElementFactory.getUiElement(this.type);
        if (!element.tryFindByDescription(description)) {
            return null;
        }

        return element;
    }
}
