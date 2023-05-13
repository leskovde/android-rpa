package com.rpa.automationframework.executors;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.Log;

import androidx.test.services.events.TimeStamp;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObject2;

import com.rpa.automationframework.Device;
import com.rpa.automationframework.controls.TextBasedUiElement;
import com.rpa.automationframework.controls.UiElement;
import com.rpa.automationframework.internal.helper.ImageUtils;

// TODO: I should check whether the incoming UiObject exists.
public class UiObjectActionExecutor implements UiActionExecutor {

    @Override
    public String getInternalType(UiElement element) {
        try {
            return element.getUiObject().getClassName();
        } catch (Exception e) {
            Log.println(Log.ERROR, "UiObjectActionExecutor", "Error getting internal type from UiObject");
        }

        return null;
    }

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

    @Override
    public void click(UiElement element) {
        UiObject control = element.getUiObject();

        try {
            control.click();
        } catch (Exception e) {
            Log.println(Log.ERROR, "UiObjectActionExecutor", "Error clicking UiObject");
        }
    }

    @Override
    public void longClick(UiElement element) {
        UiObject control = element.getUiObject();

        try {
            control.longClick();
        } catch (Exception e) {
            Log.println(Log.ERROR, "UiObjectActionExecutor", "Error long clicking UiObject");
        }
    }

    @Override
    public void setText(TextBasedUiElement element, String text) {
        UiObject control = element.getUiObject();

        try {
            control.setText(text);
        } catch (Exception e) {
            Log.println(Log.ERROR, "UiObjectActionExecutor", "Error setting text on UiObject");
        }
    }

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
