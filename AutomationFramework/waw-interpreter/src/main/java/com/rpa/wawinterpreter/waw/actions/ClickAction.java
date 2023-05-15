package com.rpa.wawinterpreter.waw.actions;

import com.rpa.wawinterpreter.waw.internal.helper.ParserHelper;
import com.rpa.wawinterpreter.waw.selectors.Selector;

import org.json.JSONObject;

public class ClickAction extends SelectorBasedAction {
    public ClickAction(JSONObject parameters) {
        if (parameters == null) throw new RuntimeException("Click action requires parameters");

        try {
            this.selectors = ParserHelper.parseSelectors(parameters);
        } catch (Exception e) {
            throw new RuntimeException("Click action requires a valid selector");
        }
    }

    @Override
    public void execute() {
        for (Selector selector : selectors) {
            selector.getUiElement().click();
        }
    }
}
