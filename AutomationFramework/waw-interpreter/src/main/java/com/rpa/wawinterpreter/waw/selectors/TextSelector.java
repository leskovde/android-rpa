package com.rpa.wawinterpreter.waw.selectors;

import com.rpa.automationframework.controls.TextBasedUiElement;
import com.rpa.automationframework.controls.UiElement;
import com.rpa.wawinterpreter.waw.factories.UiElementFactory;

public class TextSelector extends Selector {
    private final String text;

    public TextSelector(String text, TypeNames type) {
        this.type = type;
        this.text = text;
    }

    @Override
    public UiElement getUiElement() {
        try {
            TextBasedUiElement element = (TextBasedUiElement) UiElementFactory.getUiElement(this.type);
            if (!element.tryFindByContent(text)) {
                return null;
            }

            return element;
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Text selector can only be used with text-based elements");
        }
    }
}
