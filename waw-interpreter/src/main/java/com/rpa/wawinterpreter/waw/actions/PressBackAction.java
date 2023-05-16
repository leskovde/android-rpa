package com.rpa.wawinterpreter.waw.actions;

import com.rpa.automationframework.Device;

public class PressBackAction implements Action {

    @Override
    public void execute() {
        Device.getInstance().pressBack();
    }
}
