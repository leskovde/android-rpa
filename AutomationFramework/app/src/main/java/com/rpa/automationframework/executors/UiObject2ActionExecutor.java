package com.rpa.automationframework.executors;

import android.graphics.Bitmap;
import android.graphics.Rect;

import androidx.test.services.events.TimeStamp;
import androidx.test.uiautomator.UiObject2;

import com.rpa.automationframework.Device;
import com.rpa.automationframework.controls.TextBasedUiElement;
import com.rpa.automationframework.controls.UiElement;
import com.rpa.automationframework.internal.helper.ImageUtils;

// TODO: I should check whether the incoming UiObject2 exists.
public class UiObject2ActionExecutor implements UiActionExecutor {

    @Override
    public String getInternalType(UiElement element) {
        return element.getUiObject2().getClassName();
    }

    @Override
    public String getText(TextBasedUiElement element) {
        UiObject2 control = element.getUiObject2();
        return control.getText();
    }

    @Override
    public boolean isChecked(TextBasedUiElement element) {
        UiObject2 control = element.getUiObject2();
        return control.isChecked();
    }

    @Override
    public void setCheckedValue(TextBasedUiElement element, boolean checked) {
        UiObject2 control = element.getUiObject2();

        if (!control.isCheckable())
            return;

        // TODO: Is this the best way to do this?
        if (control.isChecked() != checked)
            control.click();
    }

    @Override
    public void click(UiElement element) {
        UiObject2 control = element.getUiObject2();
        control.click();
    }

    @Override
    public void longClick(UiElement element) {
        UiObject2 control = element.getUiObject2();
        control.longClick();
    }

    @Override
    public void setText(TextBasedUiElement element, String text) {
        UiObject2 control = element.getUiObject2();
        control.setText(text);
    }

    @Override
    public Bitmap getImage(UiElement element) {
        UiObject2 control = element.getUiObject2();
        Rect bounds = control.getVisibleBounds();

        Bitmap screenShot = Device.getInstance().takeScreenshot(ImageUtils.getImageName(element));
        return Bitmap.createBitmap(screenShot, bounds.left, bounds.top, bounds.width(), bounds.height());
    }
}
