package com.rpa.wawinterpreter.waw;

import com.rpa.wawinterpreter.waw.internal.helper.ParserHelper;
import com.rpa.wawinterpreter.waw.selectors.Selector;

import org.json.JSONObject;

import java.util.List;

/**
 * Represents a state locator.
 * <p>
 * An id is used to identify the state locator. It is used to reference the state locator in the
 * WAW file. The locator can also have a before and after locator.
 * <p>
 * Matching the current state is done by applying the selectors to the device.
 */
public class WherePosition {
    private final String id;
    private String before;
    private String after;
    private final List<Selector> selectors;

    public WherePosition(String id, JSONObject wherePosition) {
        this.id = id;

        try {
            this.before = wherePosition.getString("$before");
        } catch (Exception e) {
            this.before = null;
        }

        try {
            this.after = wherePosition.getString("$after");
        } catch (Exception e) {
            this.after = null;
        }

        this.selectors = ParserHelper.parseSelectors(wherePosition);
    }

    /**
     * Returns the id of the 'where' position.
     *
     * @return The id of the 'where' position.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the id of the 'before' position.
     *
     * @return The id of the 'before' position.
     */
    public String getBefore() {
        return before;
    }

    /**
     * Returns the id of the 'after' position.
     *
     * @return The id of the 'after' position.
     */
    public String getAfter() {
        return after;
    }

    /**
     * Applies the selectors to the current state of the device.
     * <p>
     * All selectors need to match in order for the state to match.
     *
     * @return True if all selectors match, false otherwise.
     */
    public boolean matchesCurrentState() {
        if (selectors.isEmpty()) {
            return false;
        }

        boolean matches = true;

        for (Selector selector : selectors) {
            matches = matches && (selector.getUiElement() != null);
        }

        return matches;
    }
}
