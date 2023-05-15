package com.rpa.wawinterpreter.waw;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Variable {
    private static final Map<String, Variable> variables = new HashMap<>();
    private final String name;
    private Object value;

    private Variable(String name) {
        this.name = name;
    }

    public static Variable getInstance(String name) {
        if (variables.containsKey(name)) {
            return variables.get(name);
        }

        Variable variable = new Variable(name);
        variables.put(name, variable);
        return variable;
    }

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
