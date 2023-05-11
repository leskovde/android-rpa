package com.rpa.automationframework.controls;

public class CalendarView extends UiElement {

    @Override
    protected boolean isInternalTypeAssignable(String internalType) {
        return internalType.toLowerCase().contains("calendarview");
    }
}
