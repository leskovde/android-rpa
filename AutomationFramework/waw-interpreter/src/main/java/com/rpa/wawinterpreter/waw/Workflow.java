package com.rpa.wawinterpreter.waw;

import com.rpa.automationframework.Device;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents the workflow of the WAW file.
 * <p>
 * A WAW file has a single workflow in which the user can define states and actions.
 * These states are stored in a map of {@link WherePosition} objects.
 * The actions are stored in a map of {@link WhatSequence} objects.
 * <p>
 * Should a state have multiple action sequences, only the first one will be executed.
 */
public class Workflow {
    private final Map<String, WherePosition> wherePositions = new HashMap<>();
    private final Map<WherePosition, List<WhatSequence>> actionSequences = new HashMap<>();

    /**
     * Registers a workflow entry.
     *
     * @param wherePosition The state of the workflow.
     * @param whatSequence  The series of actions to be executed.
     */
    public void addActionSequence(WherePosition wherePosition, WhatSequence whatSequence) {
        // TODO: Check how equals and hashCode work in Java.
        wherePositions.put(wherePosition.getId(), wherePosition);
        actionSequences.computeIfAbsent(wherePosition, k -> new ArrayList<>()).add(whatSequence);
    }

    /**
     * Navigates to the home page and returns the initial state of the workflow.
     *
     * @return The initial state of the workflow.
     */
    public String getInitialState() {
        Device.getInstance().unlockScreen();
        Device.getInstance().pressHome();
        return getNextState();
    }

    /**
     * Attempts to find a matching WherePosition object for the current state.
     *
     * @return The matching WherePosition object, or null if none was found.
     */
    public String getNextState() {
        for (Map.Entry<String, WherePosition> entry : wherePositions.entrySet()) {
            if (entry.getValue().matchesCurrentState()) {
                return entry.getKey();
            }
        }

        return null;
    }

    /**
     * Runs the action sequence of a given state.
     * <p>
     * If that state contains a 'before' and 'after' clause, those will be executed as well.
     *
     * @param state The state to be executed.
     */
    public void run(String state) {
        if (state == null || state.isEmpty()) {
            return;
        }

        WherePosition currentPosition = wherePositions.get(state);
        if (currentPosition == null) {
            throw new RuntimeException("State '" + state + "' does not exist");
        }

        run(currentPosition.getBefore());

        List<WhatSequence> sequences = actionSequences.get(currentPosition);
        if (sequences == null || sequences.isEmpty()) {
            throw new RuntimeException("State '" + state + "' does not have any actions");
        }

        // Only the first sequence is executed.
        sequences.get(0).execute();

        run(currentPosition.getAfter());
    }
}
