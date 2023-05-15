package com.rpa.wawinterpreter.waw.actions;

import com.rpa.automationframework.Device;
import com.rpa.wawinterpreter.waw.factories.SelectorFactory;
import com.rpa.wawinterpreter.waw.selectors.PositionSelector;

import org.json.JSONObject;

public class SwipeAction implements Action {
    private final PositionSelector from;
    private final PositionSelector to;

    public SwipeAction(JSONObject parameters) {
        if (parameters == null) throw new RuntimeException("Swipe action requires parameters");

        try {
            this.from = (PositionSelector) SelectorFactory.create(parameters.getJSONObject("from"));
        } catch (ClassCastException e) {
            throw new RuntimeException("The 'from' selector provided to Swipe action is not a position selector");
        } catch (Exception e) {
            throw new RuntimeException("Swipe action requires a valid 'from' selector");
        }

        try {
            this.to = (PositionSelector) SelectorFactory.create(parameters.getJSONObject("to"));
        } catch (ClassCastException e) {
            throw new RuntimeException("The 'to' selector provided to Swipe action is not a position selector");
        } catch (Exception e) {
            throw new RuntimeException("Swipe action requires a valid 'to' selector");
        }
    }

    @Override
    public void execute() {
        Device.getInstance().swipe(from.getCoordinates(), to.getCoordinates());
    }
}
