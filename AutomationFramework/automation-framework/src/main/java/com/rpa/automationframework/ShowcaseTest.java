package com.rpa.automationframework;

import android.graphics.Bitmap;
import android.util.Log;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.rpa.automationframework.controls.Button;
import com.rpa.automationframework.controls.ImageView;
import com.rpa.automationframework.controls.TextEdit;
import com.rpa.automationframework.internal.helper.ImageUtils;

import org.junit.*;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ShowcaseTest {

    @Before
    public void before() {
        Log.d("Test", "before");
    }

    @org.junit.Test
    public void test() {
        Log.d("Test", "test");

        Device.getInstance().pressHome();
        Device.getInstance().pressRecentApps();
        Device.getInstance().pressBack();

        Device.getInstance().pressVolumeUp();
        Device.getInstance().pressVolumeDown();

        Device.getInstance().lockScreen();
        Device.getInstance().lockScreen();
        Device.getInstance().unlockScreen();
        Device.getInstance().unlockScreen();

        Device.getInstance().openAppDrawer();

        Button button = new Button();
        button.tryFindByContent("UiElementShowcase");

        button.click();

        TextEdit textEdit = new TextEdit();
        textEdit.tryFindByResourceId("com.rpa.uielementshowcase:id/autoCompleteTextView");
        String text = textEdit.getText();
        Log.d("Test", text);
        textEdit.setText("Hello World");

        ImageView imageView = new ImageView();
        imageView.tryFindByResourceId("com.rpa.uielementshowcase:id/imageButton");
        Bitmap image = imageView.getImage();
        ImageUtils.saveBitmap(image, "/sdcard/Pictures/test.png");
    }

    @After
    public void after() {
        Log.d("Test", "after");
    }
}
