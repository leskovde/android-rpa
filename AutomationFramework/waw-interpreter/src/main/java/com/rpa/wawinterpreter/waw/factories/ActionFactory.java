package com.rpa.wawinterpreter.waw.factories;

import com.rpa.wawinterpreter.waw.actions.Action;
import com.rpa.wawinterpreter.waw.actions.ClickAction;
import com.rpa.wawinterpreter.waw.actions.GetImageAction;
import com.rpa.wawinterpreter.waw.actions.GetTextAction;
import com.rpa.wawinterpreter.waw.actions.GetValueAction;
import com.rpa.wawinterpreter.waw.actions.IdleAction;
import com.rpa.wawinterpreter.waw.actions.LongClickAction;
import com.rpa.wawinterpreter.waw.actions.ActionNames;
import com.rpa.wawinterpreter.waw.actions.SetTextAction;
import com.rpa.wawinterpreter.waw.actions.SetValueAction;
import com.rpa.wawinterpreter.waw.actions.SwipeAction;

import org.json.JSONObject;

public class ActionFactory {

    /**
     * Returns an action based on the action name and parameters.
     *
     * @param action     The name of the action to create.
     * @param parameters The parameters to pass to the action.
     * @return The created action.
     */
    public static Action getAction(ActionNames action, JSONObject parameters) {
        return switch (action) {
            case CLICK -> new ClickAction(parameters);
            case LONG_CLICK -> new LongClickAction(parameters);
            case SWIPE -> new SwipeAction(parameters);
            case IDLE -> new IdleAction(parameters);
            case GET_TEXT -> new GetTextAction(parameters);
            case GET_VALUE -> new GetValueAction(parameters);
            case GET_IMAGE -> new GetImageAction(parameters);
            case SET_TEXT -> new SetTextAction(parameters);
            case SET_VALUE -> new SetValueAction(parameters);
        };
    }
}
