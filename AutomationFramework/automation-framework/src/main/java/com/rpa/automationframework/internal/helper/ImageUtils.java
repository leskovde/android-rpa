package com.rpa.automationframework.internal.helper;

import android.graphics.Bitmap;

import com.rpa.automationframework.controls.UiElement;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class ImageUtils {

    /**
     * Returns a filename for the image of the given element.
     * <p>
     * The filename is composed of the element name and the current timestamp.
     *
     * @param element The element to get the image name for.
     * @return The unique filename for the image of the given element.
     */
    public static String getImageName(UiElement element) {
        return "/sdcard/Pictures/" + element + new Date().getTime() + ".png";
    }

    /**
     * Saves the given bitmap to the given filename.
     * <p>
     * The provided path must be a valid path on the device.
     * <p>
     * The bitmap is saved as a PNG file.
     *
     * @param bitmap   The bitmap to save.
     * @param filename The filename to save the bitmap to.
     */
    public static void saveBitmap(Bitmap bitmap, String filename) {
        try (FileOutputStream out = new FileOutputStream(filename)) {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
