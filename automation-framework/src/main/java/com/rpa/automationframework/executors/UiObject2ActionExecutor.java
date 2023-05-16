package com.rpa.automationframework.executors;

import android.graphics.Bitmap;
import android.graphics.Rect;

import androidx.test.uiautomator.UiObject2;

import com.rpa.automationframework.Device;
import com.rpa.automationframework.controls.TextBasedUiElement;
import com.rpa.automationframework.controls.UiElement;
import com.rpa.automationframework.internal.helper.ImageUtils;

/**
 * Executes actions on extracted UiObject2 instances.
 */
public class UiObject2ActionExecutor implements UiActionExecutor {

    /**
     * Gets the class name of the Android UI element, e.g. "android.widget.TextView".
     *
     * @param element The UI element which stores the native UI object.
     * @return The class name of the Android UI element.
     */
    @Override
    public String getInternalType(UiElement element) {
        return element.getUiObject2().getClassName();
    }

    /**
     * Gets the text content of the UI element.
     * <p>
     * This method is only applicable to UI elements which have text content.
     *
     * @param element The UI element which stores the text-based UI object.
     * @return The text content of the UI element.
     */
    @Override
    public String getText(TextBasedUiElement element) {
        UiObject2 control = element.getUiObject2();
        return control.getText();
    }

    /**
     * Checks whether a checkbox, toggle, or switch are checked.
     *
     * @param element The UI element which stores the boolean-based UI object.
     * @return The state of the checkbox, toggle, or switch.
     */
    @Override
    public boolean isChecked(TextBasedUiElement element) {
        UiObject2 control = element.getUiObject2();
        return control.isChecked();
    }

    /**
     * Sets the state of a checkbox, toggle, or switch.
     *
     * @param element The UI element which stores the boolean-based UI object.
     * @param checked The state to set the checkbox, toggle, or switch.
     */
    @Override
    public void setCheckedValue(TextBasedUiElement element, boolean checked) {
        UiObject2 control = element.getUiObject2();

        if (!control.isCheckable()) return;

        // TODO: Is this the best way of doing this?
        if (control.isChecked() != checked) control.click();
    }

    /**
     * Clicks on the UI element.
     *
     * @param element The UI element which stores the UI object.
     */
    @Override
    public void click(UiElement element) {
        UiObject2 control = element.getUiObject2();
        control.click();
    }

    /**
     * Long clicks on the UI element.
     *
     * @param element The UI element which stores the UI object.
     */
    @Override
    public void longClick(UiElement element) {
        UiObject2 control = element.getUiObject2();
        control.longClick();
    }

    /**
     * Sets the text content of the UI element.
     * <p>
     * This method is only applicable to UI elements which have editable text content.
     *
     * @param element The UI element which stores the text-based UI object.
     * @param text    The text content to be set.
     */
    @Override
    public void setText(TextBasedUiElement element, String text) {
        UiObject2 control = element.getUiObject2();
        control.setText(text);
    }

    /**
     * Gets the image of the UI element.
     * <p>
     * This is done by taking a screenshot of the device and cropping the image
     * to the bounds of the UI element.
     *
     * @param element The UI element which stores the visible UI object.
     * @return The image of the UI element.
     */
    @Override
    public Bitmap getImage(UiElement element) {
        UiObject2 control = element.getUiObject2();
        Rect bounds = control.getVisibleBounds();

        Bitmap screenShot = Device.getInstance().takeScreenshot(ImageUtils.getImageName(element));
        return Bitmap.createBitmap(screenShot, bounds.left, bounds.top, bounds.width(), bounds.height());
    }
}
