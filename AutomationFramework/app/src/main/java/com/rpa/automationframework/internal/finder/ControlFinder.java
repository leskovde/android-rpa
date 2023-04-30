package com.rpa.automationframework.internal.finder;

import com.rpa.automationframework.internal.types.RawUiElementUnion;

public interface ControlFinder {
    public RawUiElementUnion findByControlIndex(int index);

    public RawUiElementUnion findByResourceId(String resourceId);

    // TODO: Use an enum?
    public RawUiElementUnion findByClassName(String className);

    public RawUiElementUnion findByDescription(String description);
}
