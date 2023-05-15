package com.rpa.wawinterpreter.waw.actions;

import com.rpa.automationframework.controls.TextBasedUiElement;
import com.rpa.automationframework.controls.UiElement;
import com.rpa.wawinterpreter.waw.Variable;
import com.rpa.wawinterpreter.waw.internal.helper.ParserHelper;
import com.rpa.wawinterpreter.waw.selectors.Selector;

import org.json.JSONObject;

public class GetTextAction extends SelectorBasedAction {
    private final Variable variable;

    public GetTextAction(JSONObject parameters) {
        if (parameters == null) throw new RuntimeException("GetText action requires parameters");

        try {
            this.selectors = ParserHelper.parseSelectors(parameters);
        } catch (Exception e) {
            throw new RuntimeException("GetText action requires a valid selector");
        }

        try {
            this.variable = Variable.getInstance(parameters.getString("variable"));
        } catch (Exception e) {
            throw new RuntimeException("GetText action requires a valid variable name");
        }
    }

    @Override
    public void execute() {
        for (Selector selector : selectors) {
            UiElement element = selector.getUiElement();
            if (element instanceof TextBasedUiElement) {
                String text = ((TextBasedUiElement) element).getText();
                variable.setValue(text);
                return;
            }
        }
    }
}
