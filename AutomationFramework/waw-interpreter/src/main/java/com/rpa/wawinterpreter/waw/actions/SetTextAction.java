package com.rpa.wawinterpreter.waw.actions;

import com.rpa.automationframework.controls.TextBasedUiElement;
import com.rpa.automationframework.controls.TextEdit;
import com.rpa.automationframework.controls.UiElement;
import com.rpa.wawinterpreter.waw.Variable;
import com.rpa.wawinterpreter.waw.internal.helper.ParserHelper;
import com.rpa.wawinterpreter.waw.selectors.Selector;

import org.json.JSONArray;
import org.json.JSONObject;

public class SetTextAction extends Action {
    private Variable variable;
    private String text;

    public SetTextAction(JSONObject parameters) {
        try {
            this.selectors = ParserHelper.parseSelectors(parameters);
        } catch (Exception e) {
            throw new RuntimeException("SetText action requires a valid selector");
        }

        try {
            this.variable = Variable.getInstance(parameters.getString("variable"));
        } catch (Exception e) {
            // Ignore
        }

        try {
            this.text = parameters.getString("text");
        } catch (Exception e) {
            // Ignore
        }

        if (text == null && variable == null) {
            throw new RuntimeException("SetText action requires a valid text or variable");
        }
    }

    @Override
    public void execute() {
        for (Selector selector : selectors) {
            UiElement element = selector.getUiElement();
            if (element instanceof TextEdit) {
                // Prioritize text over variable.
                String value = text != null ? text : (String) variable.getValue();
                ((TextEdit) element).setText(value);
                return;
            }
        }
    }
}
