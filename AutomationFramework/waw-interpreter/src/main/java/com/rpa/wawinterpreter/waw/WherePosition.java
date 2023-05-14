package com.rpa.wawinterpreter.waw;

import com.rpa.wawinterpreter.waw.factories.SelectorFactory;
import com.rpa.wawinterpreter.waw.internal.helper.ParserHelper;
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

        this.selectors = ParserHelper.parseSelectors(wherePosition);
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
