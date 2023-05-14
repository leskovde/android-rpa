package com.rpa.wawinterpreter.waw.selectors;

import com.rpa.automationframework.controls.UiElement;
import com.rpa.wawinterpreter.waw.factories.UiElementFactory;

public class IndexSelector extends Selector {
    private final int index;

    public IndexSelector(String index, TypeNames type) {
        this.type = type;

        try {
            this.index = Integer.parseInt(index);
        } catch (Exception e) {
            throw new IllegalArgumentException("Index value in the 'index' selector must be a number");
        }
    }

    @Override
    public UiElement getUiElement() {
        UiElement element = UiElementFactory.getUiElement(this.type);
        if (!element.tryFindByIndex(index)) {
            return null;
        }

        return element;
    }
}
