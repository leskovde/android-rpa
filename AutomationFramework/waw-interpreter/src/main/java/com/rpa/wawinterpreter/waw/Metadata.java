package com.rpa.wawinterpreter.waw;

public class Metadata {
    private final String name;
    private final String description;

    public Metadata(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }
}
