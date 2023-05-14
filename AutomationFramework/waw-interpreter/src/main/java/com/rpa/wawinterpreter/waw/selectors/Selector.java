package com.rpa.wawinterpreter.waw.selectors;

import com.rpa.automationframework.controls.UiElement;

import org.json.JSONObject;

public abstract class Selector {
    protected TypeNames type;

    public abstract UiElement getUiElement();
}
