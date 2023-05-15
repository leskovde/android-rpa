package com.rpa.wawinterpreter.waw.factories;

import com.rpa.automationframework.controls.Button;
import com.rpa.automationframework.controls.CalendarView;
import com.rpa.automationframework.controls.CheckBox;
import com.rpa.automationframework.controls.ImageView;
import com.rpa.automationframework.controls.ProgressBar;
import com.rpa.automationframework.controls.SearchView;
import com.rpa.automationframework.controls.TextEdit;
import com.rpa.automationframework.controls.TextView;
import com.rpa.automationframework.controls.UiElement;
import com.rpa.automationframework.controls.WebView;
import com.rpa.wawinterpreter.waw.selectors.TypeNames;

public class UiElementFactory {

    /**
     * Constructs a new UI element of the given type.
     * @param type The type of the UI element.
     * @return The UI element.
     */
    public static UiElement getUiElement(TypeNames type) {
        return switch (type) {
            case BUTTON -> new Button();
            case CALENDAR_VIEW -> new CalendarView();
            case CHECK_BOX -> new CheckBox();
            case IMAGE_VIEW -> new ImageView();
            case PROGRESS_BAR -> new ProgressBar();
            case SEARCH_VIEW -> new SearchView();
            case TEXT_EDIT -> new TextEdit();
            case TEXT_VIEW -> new TextView();
            case WEB_VIEW -> new WebView();
        };
    }
}
