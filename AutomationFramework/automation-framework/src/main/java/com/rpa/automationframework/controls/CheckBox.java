package com.rpa.automationframework.controls;

import com.rpa.automationframework.executors.UiActionExecutor;

/**
 * Represents a checkbox, switch or toggle button.
 * <p>
 * Any UI element that has a boolean state should be represented by this class.
 */
public class CheckBox extends TextBasedUiElement {

    /**
     * Gets the value of the checkbox, switch, or toggle button.
     *
     * @return true if the checkbox is checked, toggle toggled or switch active.
     */
    public boolean isChecked() {
        UiActionExecutor executor = executors.get(state);

        if (executor != null) {
            return executor.isChecked(this);
        }

        throw new RuntimeException("No executor found for state: " + state);
    }

    /**
     * Sets the value of the checkbox, switch, or toggle button.
     *
     * @param checked true if the checkbox should be checked, toggle toggled or switch activated.
     */
    public void setCheckboxValue(boolean checked) {
        UiActionExecutor executor = executors.get(state);

        if (executor == null) {
            throw new RuntimeException("No executor found for state: " + state);
        }

        executor.setCheckedValue(this, checked);
    }

    @Override
    protected boolean isInternalTypeAssignable(String internalType) {
        if (internalType.toLowerCase().contains("checkbox")) return true;

        if (internalType.toLowerCase().contains("switch")) return true;

        return internalType.toLowerCase().contains("togglebutton");
    }
}
