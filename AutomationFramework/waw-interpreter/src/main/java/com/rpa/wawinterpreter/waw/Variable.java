package com.rpa.wawinterpreter.waw;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * A singleton class that stores variable instances throughout the execution.
 * <p>
 * A variable is identified by its name. The value of the variable can be set and retrieved.
 * The value can be of any type.
 */
public class Variable {
    private static final Map<String, Variable> variables = new HashMap<>();
    private final String name;
    private Object value;

    private Variable(String name) {
        this.name = name;
    }

    /**
     * Returns the instance of the variable with the given name.
     * <p>
     * If the variable does not exist, it is created.
     *
     * @param name The name of the variable.
     * @return The instance of the variable with the given name.
     */
    public static Variable getInstance(String name) {
        if (variables.containsKey(name)) {
            return variables.get(name);
        }

        Variable variable = new Variable(name);
        variables.put(name, variable);
        return variable;
    }

    /**
     * Returns all the variables that were get or set during the execution.
     *
     * @return All the variables.
     */
    public static Set<Map.Entry<String, Variable>> getAll() {
        return variables.entrySet();
    }

    public String getName() {
        return this.name;
    }

    public Object getValue() {
        return this.value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
