package com.rpa.wawinterpreter.waw;

/**
 * Stores the metadata of the WAW file.
 * <p>
 * The metadata contains the name and description of the workflow.
 */
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
