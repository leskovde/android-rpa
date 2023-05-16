package com.rpa.automationframework.executors;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.Log;

import androidx.test.uiautomator.UiObject;

import com.rpa.automationframework.Device;
import com.rpa.automationframework.controls.TextBasedUiElement;
import com.rpa.automationframework.controls.UiElement;
import com.rpa.automationframework.internal.helper.ImageUtils;

// TODO: I should check whether the incoming UiObject exists.

/**
 * Executes actions on extracted UiObject instances.
 */
public class UiObjectActionExecutor implements UiActionExecutor {

    /**
     * Gets the class name of the Android UI element, e.g. "android.widget.TextView".
     *
     * @param element The UI element which stores the native UI object.
     * @return The class name of the Android UI element or null if the UI element could not be retrieved.
     */
    @Override
    public String getInternalType(UiElement element) {
        try {
            return element.getUiObject().getClassName();
        } catch (Exception e) {
            Log.println(Log.ERROR, "UiObjectActionExecutor", "Error getting internal type from UiObject");
        }

        return null;
    }

    /**
     * Gets the text content of the UI element.
     * <p>
     * This method is only applicable to UI elements which have text content.
     *
     * @param element The UI element which stores the text-based UI object.
     * @return The text content of the UI element or an empty string if the UI element could not be retrieved.
     */
    @Override
    public String getText(TextBasedUiElement element) {
        UiObject control = element.getUiObject();
        String text = "";

        try {
            return control.getText();
        } catch (Exception e) {
            Log.println(Log.ERROR, "UiObjectActionExecutor", "Error getting text from UiObject");
        }

        return text;
    }

    /**
     * Checks whether a checkbox, toggle, or switch are checked.
     *
     * @param element The UI element which stores the boolean-based UI object.
     * @return The state of the checkbox, toggle, or switch. False if the UI element could not be retrieved.
     */
    @Override
    public boolean isChecked(TextBasedUiElement element) {
        UiObject control = element.getUiObject();

        try {
            return control.isChecked();
        } catch (Exception e) {
            Log.println(Log.ERROR, "UiObjectActionExecutor", "Error getting checked state from UiObject");
        }

        return false;
    }

    /**
     * Sets the state of a checkbox, toggle, or switch.
     *
     * @param element The UI element which stores the boolean-based UI object.
     * @param checked The state to set the checkbox, toggle, or switch.
     */
    @Override
    public void setCheckedValue(TextBasedUiElement element, boolean checked) {
        UiObject control = element.getUiObject();

        try {
            if (!control.isCheckable()) return;

            // TODO: Is this the best way to do this?
            if (control.isChecked() != checked) control.click();
        } catch (Exception e) {
            Log.println(Log.ERROR, "UiObjectActionExecutor", "Error setting checked state on UiObject");
        }
    }

    /**
     * Clicks on the UI element.
     *
     * @param element The UI element which stores the UI object.
     */
    @Override
    public void click(UiElement element) {
        UiObject control = element.getUiObject();

        try {
            control.click();
        } catch (Exception e) {
            Log.println(Log.ERROR, "UiObjectActionExecutor", "Error clicking UiObject");
        }
    }

    /**
     * Long clicks on the UI element.
     *
     * @param element The UI element which stores the UI object.
     */
    @Override
    public void longClick(UiElement element) {
        UiObject control = element.getUiObject();

        try {
            control.longClick();
        } catch (Exception e) {
            Log.println(Log.ERROR, "UiObjectActionExecutor", "Error long clicking UiObject");
        }
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
        UiObject control = element.getUiObject();

        try {
            control.setText(text);
        } catch (Exception e) {
            Log.println(Log.ERROR, "UiObjectActionExecutor", "Error setting text on UiObject");
        }
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
        UiObject control = element.getUiObject();
        Rect bounds;
        try {
            bounds = control.getBounds();
        } catch (Exception e) {
            Log.println(Log.ERROR, "UiObjectActionExecutor", "Error getting bounds from UiObject");
            return null;
        }

        Bitmap screenShot = Device.getInstance().takeScreenshot(ImageUtils.getImageName(element));
        return Bitmap.createBitmap(screenShot, bounds.left, bounds.top, bounds.width(), bounds.height());
    }
}
