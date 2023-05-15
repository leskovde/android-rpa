package com.rpa.wawinterpreter.waw.actions;

import com.rpa.wawinterpreter.waw.selectors.Selector;

import java.util.List;

/**
 * Represents an action that can be executed on an UI element.
 * <p>
 * The element is identified by a list of selectors. The first matching element is used.
 */
public abstract class SelectorBasedAction implements Action {
    protected List<Selector> selectors;

    public List<Selector> getSelectors() {
        return selectors;
    }
}
