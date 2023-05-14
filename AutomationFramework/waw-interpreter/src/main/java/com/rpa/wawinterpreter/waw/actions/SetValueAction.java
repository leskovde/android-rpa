package com.rpa.wawinterpreter.waw.actions;

import com.rpa.automationframework.controls.CheckBox;
import com.rpa.automationframework.controls.TextEdit;
import com.rpa.automationframework.controls.UiElement;
import com.rpa.wawinterpreter.waw.Variable;
import com.rpa.wawinterpreter.waw.internal.helper.ParserHelper;
import com.rpa.wawinterpreter.waw.selectors.Selector;

import org.json.JSONObject;

public class SetValueAction extends SelectorBasedAction {
    private Variable variable;
    private Boolean value;

    public SetValueAction(JSONObject parameters) {
        try {
            this.selectors = ParserHelper.parseSelectors(parameters);
        } catch (Exception e) {
            throw new RuntimeException("SetValue action requires a valid selector");
        }

        try {
            this.variable = Variable.getInstance(parameters.getString("variable"));
        } catch (Exception e) {
            // Ignore
        }

        try {
            this.value = parameters.getBoolean("value");
        } catch (Exception e) {
            // Ignore
        }

        if (value == null && variable == null) {
            throw new RuntimeException("SetValue action requires a valid 'value' or 'variable'");
        }
    }

    @Override
    public void execute() {
        for (Selector selector : selectors) {
            UiElement element = selector.getUiElement();
            if (element instanceof CheckBox) {
                // Prioritize text over variable.
                Boolean value = this.value != null ? this.value : (Boolean) variable.getValue();
                ((CheckBox) element).setCheckboxValue(value);
                return;
            }
        }
    }
}
