package com.rpa.wawinterpreter.waw;

import com.rpa.wawinterpreter.waw.factories.SelectorFactory;
import com.rpa.wawinterpreter.waw.selectors.Selector;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class WherePosition {
    private String id;
    private String before;
    private String after;
    private List<Selector> selectors;

    public WherePosition(String id, JSONObject wherePosition) {
        this.id = id;

        try {
            this.before = wherePosition.getString("$before");
        } catch (Exception e) {
            this.before = null;
        }

        try {
            this.after = wherePosition.getString("after");
        } catch (Exception e) {
            this.after = null;
        }

        JSONArray selectors;
        try {
            selectors = wherePosition.getJSONArray("selectors");
        } catch (Exception e) {
            throw new RuntimeException("WherePosition must have a selectors array");
        }

        for (int i = 0; i < selectors.length(); i++) {
            JSONObject selector;
            try {
                selector = selectors.getJSONObject(i);
            } catch (Exception e) {
                throw new RuntimeException("Selectors in 'where' must be JSON objects");
            }

            this.selectors.add(SelectorFactory.create(selector));
        }
    }

    public String getId() {
        return id;
    }

    public String getBefore() {
        return before;
    }

    public String getAfter() {
        return after;
    }

    public boolean matchesCurrentState() {
        boolean matches = true;

        for (Selector selector : selectors) {
            matches = matches && (selector.getUiElement() != null);
        }

        return matches;
    }
}
