package com.rpa.wawinterpreter.waw.actions;

import com.rpa.automationframework.Device;

public class LockScreenAction implements Action {
    @Override
    public void execute() {
        Device.getInstance().lockScreen();
    }
}
