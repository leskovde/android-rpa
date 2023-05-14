package com.rpa.wawinterpreter.waw.factories;

import com.rpa.wawinterpreter.waw.selectors.ClassNameSelector;
import com.rpa.wawinterpreter.waw.selectors.DescriptionSelector;
import com.rpa.wawinterpreter.waw.selectors.IndexSelector;
import com.rpa.wawinterpreter.waw.selectors.ResIdSelector;
import com.rpa.wawinterpreter.waw.selectors.SelectorNames;
import com.rpa.wawinterpreter.waw.selectors.Selector;
import com.rpa.wawinterpreter.waw.selectors.TextSelector;
import com.rpa.wawinterpreter.waw.selectors.TypeNames;

import org.json.JSONObject;

import java.util.Iterator;

public class SelectorFactory {
    public static Selector create(JSONObject selector, TypeNames type) {
        SelectorNames selectorName = getSelectorName(selector);
        String parameter;
        try {
            parameter = selector.getString(selectorName.name());
        } catch (Exception e) {
            throw new RuntimeException("Selector must have a parameter");
        }

        return switch (selectorName) {
            case INDEX -> new IndexSelector(parameter, type);
            case RES_ID -> new ResIdSelector(parameter, type);
            case CLASS_NAME -> new ClassNameSelector(parameter, type);
            case DESC -> new DescriptionSelector(parameter, type);
            case TEXT -> new TextSelector(parameter, type);
        };
    }

    private static SelectorNames getSelectorName(JSONObject selector) {
        Iterator<String> keys = selector.keys();

        while (keys.hasNext()) {
            String key = keys.next();
            try {
                return SelectorNames.valueOf(key);
            } catch (Exception e) {
                // Ignore
            }
        }

        throw new RuntimeException("Selector must have a valid name");
    }
}
