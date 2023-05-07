package com.rpa.automationframework.finders;

import com.rpa.automationframework.internal.types.RawUiElementUnion;

import java.util.List;

public interface ControlFinder {
    public RawUiElementUnion findByControlIndex(int index);

    public RawUiElementUnion findByResourceId(String resourceId);

    // TODO: Use an enum?
    public List<RawUiElementUnion> findByClassName(String className);

    public RawUiElementUnion findByDescription(String description);

    public List<RawUiElementUnion> findByTextContent(String content);
}
