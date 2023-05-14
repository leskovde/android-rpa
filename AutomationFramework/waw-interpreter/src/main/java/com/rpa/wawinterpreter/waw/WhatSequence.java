package com.rpa.wawinterpreter.waw;

import com.rpa.wawinterpreter.waw.actions.Action;
import com.rpa.wawinterpreter.waw.actions.ActionNames;
import com.rpa.wawinterpreter.waw.factories.ActionFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class WhatSequence {
    private List<Action> actions;

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
                // TODO: All calls to valueOf should have the arg converted to uppercase
                actionName = ActionNames.valueOf(whatItem.getString("action"));
            } catch (JSONException e) {
                throw new RuntimeException("The provided WAW input contains a workflow item that does not contain an 'action' name", e);
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("The workflow item does not contain a valid action name", e);
            }

            JSONArray parameters;
            try {
                parameters = whatItem.getJSONArray("args");
            } catch (JSONException e) {
                throw new RuntimeException("The provided WAW input contains a 'what' item that does not contain an 'args' array", e);
            }

            Action action = ActionFactory.getAction(actionName, parameters);
            actions.add(action);
        }
    }

    public void execute() {
        for (Action action : actions) {
            action.execute();
        }
    }
}
