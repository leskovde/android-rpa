package com.rpa.automationframework.controls;

import com.rpa.automationframework.executors.UiActionExecutor;

public class CheckBox extends TextBasedUiElement {
    public boolean isChecked() {
        UiActionExecutor executor = executors.get(state);

        if (executor != null) {
            return executor.isChecked(this);
        }

        throw new RuntimeException("No executor found for state: " + state);
    }

    public void setCheckboxValue(boolean checked) {
        UiActionExecutor executor = executors.get(state);

        if (executor == null) {
            throw new RuntimeException("No executor found for state: " + state);
        }

        executor.setCheckedValue(this, checked);
    }

    @Override
    protected boolean isInternalTypeAssignable(Class<?> internalType) {
        return false;
    }
}
