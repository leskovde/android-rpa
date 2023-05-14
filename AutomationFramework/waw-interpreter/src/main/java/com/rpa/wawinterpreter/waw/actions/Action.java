package com.rpa.wawinterpreter.waw.actions;

import com.rpa.wawinterpreter.waw.selectors.Selector;

public interface Action {
    void execute();
    Selector getSelector();
}
