package com.rpa.automationframework.internal.helper;

import android.graphics.Bitmap;

import com.rpa.automationframework.controls.UiElement;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class ImageUtils {

    public static String getImageName(UiElement element) {
        return "/sdcard/Pictures/" + element + new Date().getTime() + ".png";
    }

    public static void saveBitmap(Bitmap bitmap, String filename) {
        try (FileOutputStream out = new FileOutputStream(filename)) {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
