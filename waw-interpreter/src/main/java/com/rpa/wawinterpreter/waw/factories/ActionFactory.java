package com.rpa.wawinterpreter.waw.factories;

import com.rpa.wawinterpreter.waw.actions.Action;
import com.rpa.wawinterpreter.waw.actions.ClickAction;
import com.rpa.wawinterpreter.waw.actions.GetImageAction;
import com.rpa.wawinterpreter.waw.actions.GetTextAction;
import com.rpa.wawinterpreter.waw.actions.GetValueAction;
import com.rpa.wawinterpreter.waw.actions.IdleAction;
import com.rpa.wawinterpreter.waw.actions.LockScreenAction;
import com.rpa.wawinterpreter.waw.actions.LongClickAction;
import com.rpa.wawinterpreter.waw.actions.ActionNames;
import com.rpa.wawinterpreter.waw.actions.OpenAppDrawerAction;
import com.rpa.wawinterpreter.waw.actions.OpenNotificationsAction;
import com.rpa.wawinterpreter.waw.actions.PressBackAction;
import com.rpa.wawinterpreter.waw.actions.PressHomeAction;
import com.rpa.wawinterpreter.waw.actions.PressRecentAppsAction;
import com.rpa.wawinterpreter.waw.actions.SetTextAction;
import com.rpa.wawinterpreter.waw.actions.SetValueAction;
import com.rpa.wawinterpreter.waw.actions.SwipeAction;
import com.rpa.wawinterpreter.waw.actions.UnlockScreenAction;
import com.rpa.wawinterpreter.waw.actions.VolumeDownAction;
import com.rpa.wawinterpreter.waw.actions.VolumeUpAction;

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
            case HOME -> new PressHomeAction();
            case BACK -> new PressBackAction();
            case RECENT_APPS -> new PressRecentAppsAction();
            case VOLUME_UP -> new VolumeUpAction();
            case VOLUME_DOWN -> new VolumeDownAction();
            case LOCK_SCREEN -> new LockScreenAction();
            case UNLOCK_SCREEN -> new UnlockScreenAction();
            case OPEN_NOTIFICATIONS -> new OpenNotificationsAction();
            case OPEN_APP_DRAWER -> new OpenAppDrawerAction();
        };
    }
}
