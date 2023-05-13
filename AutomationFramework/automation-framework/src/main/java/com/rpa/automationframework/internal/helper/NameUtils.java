package com.rpa.automationframework.internal.helper;

import java.util.ArrayList;
import java.util.List;

public class NameUtils {
    public static List<String> getClassNames(String className) {
        List<String> names = new ArrayList<>();
        names.add(className);

        while (className.contains(".")) {
            className = className.substring(className.indexOf(".") + 1, className.length() - 1);

            if (className == null || className.isEmpty() || names.contains(className))
                break;

            names.add(className);
        }

        return names;
    }
}
