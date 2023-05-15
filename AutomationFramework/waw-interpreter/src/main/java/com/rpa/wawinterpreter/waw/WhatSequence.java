package com.rpa.wawinterpreter.waw;

import com.rpa.wawinterpreter.waw.actions.Action;
import com.rpa.wawinterpreter.waw.actions.ActionNames;
import com.rpa.wawinterpreter.waw.factories.ActionFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a sequence of actions.
 */
public class WhatSequence {
    private final List<Action> actions = new ArrayList<>();

    public WhatSequence(JSONArray what) {
        for (int i = 0; i < what.length(); i++) {
            JSONObject whatItem;
            try {
                whatItem = what.getJSONObject(i);
            } catch (Exception e) {
                throw new RuntimeException("The provided WAW input contains a workflow item that contains a 'what' item that is not a JSON object", e);
            }

            ActionNames actionName;
            try {
                actionName = ActionNames.valueOf(whatItem.getString("action").toUpperCase());
            } catch (JSONException e) {
                throw new RuntimeException("The provided WAW input contains a workflow item that does not contain an 'action' name", e);
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("The workflow item does not contain a valid action name", e);
            }

            JSONObject parameters = null;
            try {
                parameters = whatItem.getJSONObject("args");
            } catch (JSONException e) {
                // Ignore
            }

            Action action = ActionFactory.getAction(actionName, parameters);
            actions.add(action);
        }
    }

    /**
     * Runs the sequence of actions in the order they were defined.
     */
    public void execute() {
        for (Action action : actions) {
            action.execute();
        }
    }
}
