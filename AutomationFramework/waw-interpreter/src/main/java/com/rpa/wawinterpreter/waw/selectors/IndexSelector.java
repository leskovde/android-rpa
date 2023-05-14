package com.rpa.wawinterpreter.waw.selectors;

import com.rpa.automationframework.controls.UiElement;

public class IndexSelector implements Selector {
    public IndexSelector(String index) {
        int indexInt = Integer.parseInt(index);
    }

    @Override
    public UiElement getUiElement() {
        return null;
    }
}
