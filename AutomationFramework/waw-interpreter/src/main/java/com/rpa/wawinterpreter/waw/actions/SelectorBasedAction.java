package com.rpa.wawinterpreter.waw.actions;

import com.rpa.wawinterpreter.waw.selectors.Selector;

import java.util.List;

public abstract class SelectorBasedAction implements Action {
    protected List<Selector> selectors;

    public List<Selector> getSelectors() {
        return selectors;
    }
}
