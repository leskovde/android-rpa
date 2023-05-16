package com.rpa.automationframework.internal.helper;

import java.util.ArrayList;
import java.util.List;

public class NameUtils {

    /**
     * Splits a class name into its parts.
     * <p>
     * Returns a list of class names, starting with the full class name and ending with the class name without the package.
     *
     * @param className The class name to split.
     * @return The list of class name suffixes.
     */
    public static List<String> getClassNames(String className) {
        List<String> names = new ArrayList<>();
        names.add(className);

        while (className.contains(".")) {
            className = className.substring(className.indexOf(".") + 1, className.length() - 1);

            if (className == null || className.isEmpty() || names.contains(className)) break;

            names.add(className);
        }

        return names;
    }
}
