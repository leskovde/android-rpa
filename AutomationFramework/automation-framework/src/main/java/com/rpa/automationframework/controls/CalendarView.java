package com.rpa.automationframework.controls;

/**
 * Represents a calendar view.
 * <p>
 * There are no methods unique to this control. Use methods inherited from {@link UiElement}.
 */
public class CalendarView extends UiElement {

    @Override
    protected boolean isInternalTypeAssignable(String internalType) {
        return internalType.toLowerCase().contains("calendarview");
    }
}
