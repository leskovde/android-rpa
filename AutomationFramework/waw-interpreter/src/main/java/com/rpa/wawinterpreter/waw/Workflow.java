package com.rpa.wawinterpreter.waw;

import com.rpa.automationframework.Device;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Workflow {
    private final Map<String, WherePosition> wherePositions = new HashMap<>();
    private final Map<WherePosition, List<WhatSequence>> actionSequences = new HashMap<>();

    public void addActionSequence(WherePosition wherePosition, WhatSequence whatSequence) {
        // TODO: Check how equals and hashCode work in Java.
        wherePositions.put(wherePosition.getId(), wherePosition);
        actionSequences.computeIfAbsent(wherePosition, k -> new ArrayList<>()).add(whatSequence);
    }

    public String getInitialState() {
        Device.getInstance().pressHome();
        return getNextState();
    }

    public String getNextState() {
        for (Map.Entry<String, WherePosition> entry : wherePositions.entrySet()) {
            if (entry.getValue().matchesCurrentState()) {
                return entry.getKey();
            }
        }

        return null;
    }

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
