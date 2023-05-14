package com.rpa.wawinterpreter.waw;

import com.rpa.wawinterpreter.waw.actions.Action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Workflow {
    private final Map<WherePosition, List<WhatSequence>> actionSequences = new HashMap<>();

    public void addActionSequence(WherePosition wherePosition, WhatSequence whatSequence) {
        // TODO: Check how equals and hashCode work in Java.
        actionSequences.computeIfAbsent(wherePosition, k -> new ArrayList<>()).add(whatSequence);
    }
}
