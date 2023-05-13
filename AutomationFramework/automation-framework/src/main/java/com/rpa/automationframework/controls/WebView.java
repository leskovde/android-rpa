package com.rpa.automationframework.controls;

public class WebView extends UiElement {

    @Override
    protected boolean isInternalTypeAssignable(String internalType) {
        return internalType.toLowerCase().contains("webview");
    }
}
