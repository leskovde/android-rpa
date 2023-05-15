package com.rpa.wawinterpreter.waw.actions;

import com.rpa.automationframework.Device;

import org.json.JSONObject;

public class IdleAction implements Action {
    private final int idleTime;

    public IdleAction(JSONObject parameters) {
        if (parameters == null) throw new RuntimeException("Idle action requires parameters");

        try {
            this.idleTime = parameters.getInt("duration");
        } catch (Exception e) {
            throw new RuntimeException("Idle action requires a valid duration");
        }
    }

    @Override
    public void execute() {
        Device.getInstance().idle(idleTime);
    }
}
