package com.rpa.wawinterpreter.waw.actions;

/**
 * Represents an action that can be executed on the device or on an UI element.
 */
public interface Action {

    /**
     * Executes the action, possibly changing the state of the device.
     */
    void execute();
}
