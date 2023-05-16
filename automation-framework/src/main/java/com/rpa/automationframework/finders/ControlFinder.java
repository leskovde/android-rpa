package com.rpa.automationframework.finders;

import com.rpa.automationframework.internal.types.RawUiElementUnion;

import java.util.List;

/**
 * Serves to find controls through various means.
 */
public interface ControlFinder {
    RawUiElementUnion findByControlIndex(int index);

    RawUiElementUnion findByResourceId(String resourceId);

    List<RawUiElementUnion> findByClassName(String className);

    List<RawUiElementUnion> findByDescription(String description);

    List<RawUiElementUnion> findByTextContent(String content);

    RawUiElementUnion findByPosition(int x, int y);
}
