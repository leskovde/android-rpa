package com.rpa.wawinterpreter.waw.selectors;

import com.rpa.automationframework.controls.UiElement;
import com.rpa.automationframework.internal.types.AbsoluteCoordinates;
import com.rpa.wawinterpreter.waw.factories.UiElementFactory;

public class PositionSelector extends Selector {
    private final int x;
    private final int y;

    public PositionSelector(String parameter, TypeNames type) {
        this.type = type;

        String[] coordinates = parameter.split(",");
        try {
            this.x = Integer.parseInt(coordinates[0]);
            this.y = Integer.parseInt(coordinates[1]);
        } catch (Exception e) {
            throw new IllegalArgumentException("Coordinates in the 'position' selector must be integers");
        }
    }

    @Override
    public UiElement getUiElement() {
        UiElement element = UiElementFactory.getUiElement(this.type);
        if (!element.tryFindByPosition(new AbsoluteCoordinates(x, y))) {
            return null;
        }

        return element;
    }

    public AbsoluteCoordinates getCoordinates() {
        return new AbsoluteCoordinates(x, y);
    }
}
