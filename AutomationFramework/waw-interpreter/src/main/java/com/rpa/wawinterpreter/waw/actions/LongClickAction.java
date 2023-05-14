package com.rpa.wawinterpreter.waw.actions;

import com.rpa.wawinterpreter.waw.internal.helper.ParserHelper;
import com.rpa.wawinterpreter.waw.selectors.Selector;

import org.json.JSONObject;

public class LongClickAction extends Action {
    public LongClickAction(JSONObject parameters) {
        try {
            this.selectors = ParserHelper.parseSelectors(parameters);
        } catch (Exception e) {
            throw new RuntimeException("LongClick action requires a valid selector");
        }
    }

    @Override
    public void execute() {
        for (Selector selector : this.selectors) {
            selector.getUiElement().longClick();
        }
    }
}
