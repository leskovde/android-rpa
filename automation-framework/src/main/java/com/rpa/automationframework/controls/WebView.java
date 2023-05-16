package com.rpa.automationframework.controls;

/**
 * Represents a web view.
 * <p>
 * There are no methods unique to this control. Use methods inherited from {@link UiElement}.
 */
public class WebView extends UiElement {

    @Override
    protected boolean isInternalTypeAssignable(String internalType) {
        return internalType.toLowerCase().contains("webview");
    }
}
