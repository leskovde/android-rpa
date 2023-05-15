package com.rpa.wawinterpreter.waw;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Parses and stores the WAW file.
 * <p>
 * The storage consists of the metadata and the workflow. The workflow can be executed.
 * <p>
 * If the parsing fails, a runtime exception with an appropriate error is thrown.
 */
public class MappedWaw {
    private final Metadata metadata;
    private final Workflow workflow;

    public MappedWaw(String wawInput) {
        JSONObject waw;
        JSONObject metaData;
        JSONArray workflowArray;

        try {
            waw = new JSONObject(wawInput);
        } catch (Exception e) {
            throw new RuntimeException("The provided WAW input is not valid JSON", e);
        }

        metadata = parseMetadata(waw);

        try {
            workflowArray = waw.getJSONArray("workflow");
        } catch (Exception e) {
            throw new RuntimeException("The provided WAW input does not contain a 'workflow' array", e);
        }

        workflow = new Workflow();

        for (int i = 0; i < workflowArray.length(); i++) {
            JSONObject workflowItem;
            try {
                workflowItem = workflowArray.getJSONObject(i);
            } catch (Exception e) {
                throw new RuntimeException("The provided WAW input contains a workflow item that is not a JSON object", e);
            }

            String id;
            JSONObject where;
            JSONArray what;

            try {
                id = workflowItem.getString("id");
            } catch (Exception e) {
                throw new RuntimeException("The provided WAW input contains a workflow item that does not contain an 'id'", e);
            }

            try {
                where = workflowItem.getJSONObject("where");
            } catch (Exception e) {
                throw new RuntimeException("The provided WAW input contains a workflow item that does not contain a 'where' object", e);
            }

            WherePosition whereParsed = new WherePosition(id, where);

            try {
                what = workflowItem.getJSONArray("what");
            } catch (Exception e) {
                throw new RuntimeException("The provided WAW input contains a workflow item that does not contain a 'what' array", e);
            }

            WhatSequence whatParsed = new WhatSequence(what);
            workflow.addActionSequence(whereParsed, whatParsed);
        }
    }

    /**
     * Returns the name of the workflow as defined in the metadata.
     *
     * @return The name of the workflow.
     */
    public String getName() {
        return metadata.getName();
    }

    /**
     * Returns the description of the workflow as defined in the metadata.
     *
     * @return The description of the workflow.
     */
    public String getDescription() {
        return metadata.getDescription();
    }

    /**
     * Executes the workflow from the initial state.
     */
    public void run() {
        String state = workflow.getInitialState();

        do {
            workflow.run(state);
        } while ((state = workflow.getNextState()) != null);
    }

    @NonNull
    private Metadata parseMetadata(JSONObject waw) {
        Metadata parsedMetadata;
        JSONObject metadata;
        try {
            metadata = waw.getJSONObject("meta");
        } catch (Exception e) {
            throw new RuntimeException("The provided WAW input does not contain a 'meta' object", e);
        }

        try {
            parsedMetadata = new Metadata(metadata.getString("name"), metadata.getString("desc"));
        } catch (Exception e) {
            throw new RuntimeException("The provided WAW input does not contain a 'name' or 'desc'", e);
        }

        return parsedMetadata;
    }
}
