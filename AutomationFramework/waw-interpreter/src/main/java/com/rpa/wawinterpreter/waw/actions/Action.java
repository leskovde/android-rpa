package com.rpa.wawinterpreter.waw.actions;

import com.rpa.wawinterpreter.waw.selectors.Selector;

import java.util.List;

public abstract class Action {
    protected List<Selector> selectors;

    public abstract void execute();
    public List<Selector> getSelectors() {
        return selectors;
    }
}
