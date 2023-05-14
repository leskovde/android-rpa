package com.rpa.wawinterpreter.waw;

import org.json.JSONArray;
import org.json.JSONObject;

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

        try {
            metaData = waw.getJSONObject("meta");
        } catch (Exception e) {
            throw new RuntimeException("The provided WAW input does not contain a 'meta' object", e);
        }

        try {
            metadata = new Metadata(metaData.getString("name"), metaData.getString("description"));
        } catch (Exception e) {
            throw new RuntimeException("The provided WAW input does not contain a 'name' or 'description'", e);
        }

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
}
