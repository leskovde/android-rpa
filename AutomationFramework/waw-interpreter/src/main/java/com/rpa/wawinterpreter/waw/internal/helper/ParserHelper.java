package com.rpa.wawinterpreter.waw.internal.helper;

import com.rpa.wawinterpreter.waw.factories.SelectorFactory;
import com.rpa.wawinterpreter.waw.selectors.Selector;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ParserHelper {

    /**
     * Parses the selectors from the provided JSON object.
     * <p>
     * The selectors are expected to be in an array called 'selectors'.
     *
     * @param object The JSON object to parse the selectors from.
     * @return The parsed selectors.
     */
    public static List<Selector> parseSelectors(JSONObject object) {
        List<Selector> parsedSelectors = new ArrayList<>();

        JSONArray selectors;
        try {
            selectors = object.getJSONArray("selectors");
        } catch (Exception e) {
            throw new RuntimeException("Could not find the selectors array");
        }

        for (int i = 0; i < selectors.length(); i++) {
            JSONObject selector;
            try {
                selector = selectors.getJSONObject(i);
            } catch (Exception e) {
                throw new RuntimeException("Selectors must be JSON objects");
            }

            parsedSelectors.add(SelectorFactory.create(selector));
        }

        return parsedSelectors;
    }
}
