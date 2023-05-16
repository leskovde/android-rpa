package com.rpa.automationframework;

import android.app.Instrumentation;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.RemoteException;
import android.util.Log;
import android.view.KeyEvent;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;

import com.rpa.automationframework.finders.BySelectorControlFinder;
import com.rpa.automationframework.finders.ControlFinder;
import com.rpa.automationframework.finders.UiSelectorControlFinder;
import com.rpa.automationframework.internal.types.Position;
import com.rpa.automationframework.internal.types.RelativePosition;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A singleton class that provides access to the device under test.
 */
public final class Device {
    private final UiDevice uiDevice;
    private int width;
    private int height;
    public List<ControlFinder> controlFinders;

    private Device() {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        uiDevice = UiDevice.getInstance(instrumentation);

        this.controlFinders = new ArrayList<>();
        controlFinders.add(new UiSelectorControlFinder(uiDevice));
        controlFinders.add(new BySelectorControlFinder(uiDevice));
        controlFinders = Collections.unmodifiableList(controlFinders);
    }

    private static final class InstanceHolder {
        private static final Device instance = new Device();
    }

    public static Device getInstance() {
        return InstanceHolder.instance;
    }

    /**
     * Attempts to press the back button a given number of times.
     * <p>
     * If the attempt is successful, the method returns immediately. If the attempt fails, the method will sleep for 1
     * second and then try again. This will be repeated until the attempt is successful or the given number of tries
     * has been reached.
     *
     * @param numberOfTries The number of times to try pressing the back button.
     */
    public void pressBack(int numberOfTries) {
        if (numberOfTries < 1) {
            throw new IllegalArgumentException("numberOfTries must be greater than 0");
        }

        // Unroll the first iteration to avoid sleeping.
        if (uiDevice.pressBack()) {
            return;
        }

        for (int i = 1; i < numberOfTries; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Log.println(Log.ERROR, "Device", "Interrupted while pressing back: " + e.getMessage());
            }

            if (uiDevice.pressBack()) {
                return;
            }
        }

        Log.println(Log.WARN, "Device", "Failed to press the back button - there might not be a dialog to go back to.");
    }

    /**
     * Attempts to press the back button once and waits for the device to become idle.
     */
    public void pressBack() {
        pressBack(1);
        uiDevice.waitForIdle();
    }

    /**
     * Attempts to press the home button a given number of times.
     * <p>
     * If the attempt is successful, the method returns immediately. If the attempt fails, the method will sleep for 1
     * second and then try again. This will be repeated until the attempt is successful or the given number of tries
     * has been reached.
     *
     * @param numberOfTries The number of times to try pressing the home button.
     */
    public void pressHome(int numberOfTries) {
        if (numberOfTries < 1) {
            throw new IllegalArgumentException("numberOfTries must be greater than 0");
        }

        // Unroll the first iteration to avoid sleeping.
        if (uiDevice.pressHome()) {
            return;
        }

        for (int i = 1; i < numberOfTries; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Log.println(Log.ERROR, "Device", "Interrupted while pressing home: " + e.getMessage());
            }

            if (uiDevice.pressHome()) {
                return;
            }
        }

        Log.println(Log.WARN, "Device", "Failed to press the home button - the device might already be on the home screen.");
    }

    /**
     * Attempts to press the home button once and waits for the device to become idle.
     */
    public void pressHome() {
        pressHome(1);
        uiDevice.waitForIdle();
    }

    /**
     * Attempts to press the recent apps button a given number of times.
     * <p>
     * If the attempt is successful, the method returns immediately. If the attempt fails, the method will sleep for 1
     * second and then try again. This will be repeated until the attempt is successful or the given number of tries
     * has been reached.
     *
     * @param numberOfTries The number of times to try pressing the recent apps button.
     */
    public void pressRecentApps(int numberOfTries) {
        if (numberOfTries < 1) {
            throw new IllegalArgumentException("numberOfTries must be greater than 0");
        }

        // Unroll the first iteration to avoid sleeping.
        try {
            if (uiDevice.pressRecentApps()) {
                return;
            }
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

        for (int i = 1; i < numberOfTries; i++) {
            try {
                Thread.sleep(1000);

                if (uiDevice.pressRecentApps()) {
                    return;
                }

            } catch (Exception e) {
                Log.println(Log.ERROR, "Device", "Failed to press the recent apps button: " + e.getMessage());
            }
        }

        Log.println(Log.WARN, "Device", "Failed to press the recent apps button.");
    }

    /**
     * Attempts to press the recent apps button once and waits for the device to become idle.
     */
    public void pressRecentApps() {
        pressRecentApps(1);
        uiDevice.waitForIdle();
    }

    /**
     * Presses the volume up button.
     */
    public void pressVolumeUp() {
        uiDevice.pressKeyCode(KeyEvent.KEYCODE_VOLUME_UP);
    }

    /**
     * Presses the volume down button.
     */
    public void pressVolumeDown() {
        uiDevice.pressKeyCode(KeyEvent.KEYCODE_VOLUME_DOWN);
    }

    /**
     * Presses the power/lock button.
     */
    public void pressPower() {
        uiDevice.pressKeyCode(KeyEvent.KEYCODE_POWER);
        uiDevice.waitForIdle();
    }

    /**
     * Attempts to lock the screen.
     * <p>
     * If the screen is already locked, the method returns immediately.
     */
    public void lockScreen() {
        try {
            uiDevice.sleep();
        } catch (RemoteException e) {
            throw new RuntimeException("Failed to lock the screen: " + e.getMessage());
        }

        uiDevice.waitForIdle();
    }

    /**
     * Attempts to unlock the screen.
     * <p>
     * If the screen is already unlocked, the method returns immediately.
     */
    public void unlockScreen() {
        try {
            uiDevice.wakeUp();
        } catch (RemoteException e) {
            throw new RuntimeException("Failed to unlock the screen: " + e.getMessage());
        }

        // An idle wait is automatically inserted by wakeUp().
    }

    /**
     * Returns the width of the display, in pixels.
     *
     * @return The width of the display, in pixels.
     */
    public int getDisplayWidth() {
        if (width == 0) {
            // TODO: Does rotation play a role in this?
            width = uiDevice.getDisplayWidth();
        }

        return width;
    }

    /**
     * Returns the height of the display, in pixels.
     *
     * @return The height of the display, in pixels.
     */
    public int getDisplayHeight() {
        if (height == 0) {
            // TODO: Does rotation play a role in this?
            height = uiDevice.getDisplayHeight();
        }

        return height;
    }

    /**
     * Clicks on the specified position.
     *
     * @param position The absolute or relative position to click on.
     */
    public void click(Position position) {
        uiDevice.click(position.getX(), position.getY());
    }

    /**
     * Performs a long click on the specified position.
     * <p>
     * This method will swipe from the specified position to the same position over a duration of 0.5 seconds.
     *
     * @param position The absolute or relative position to click on.
     */
    public void longClick(Position position) {
        int x = position.getX();
        int y = position.getY();

        // 100 steps takes about 0.5 seconds to complete.
        uiDevice.swipe(x, y, x, y, 100);
    }

    /**
     * Swipes from one position to another.
     * <p>
     * The duration of the swipe is calculated based on the distance between the two positions.
     *
     * @param from The absolute or relative position to start the swipe from.
     * @param to   The absolute or relative position to end the swipe at.
     */
    public void swipe(Position from, Position to) {
        int steps = (int) (Math.sqrt(Math.pow(from.getX() - to.getX(), 2) + Math.pow(from.getY() - to.getY(), 2)) / 100 + 1);
        uiDevice.swipe(from.getX(), from.getY(), to.getX(), to.getY(), steps);
        uiDevice.waitForIdle();
    }

    /**
     * Waits a specified amount of time.
     *
     * @param ms The number of milliseconds to wait.
     */
    public void idle(long ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception e) {
            Log.println(Log.ERROR, "Device", "Failed to idle: " + e.getMessage());
        }
    }

    /**
     * Takes a screenshot and saves it to the specified file.
     * <p>
     * The file is then loaded into memory and returned as a Bitmap.
     *
     * @param filename The filename to save the screenshot to.
     * @return The screenshot as a Bitmap.
     */
    public Bitmap takeScreenshot(String filename) {
        if (!uiDevice.takeScreenshot(new File(filename))) {
            Log.println(Log.ERROR, "Device", "Failed to take screenshot");
            return null;
        }

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        return BitmapFactory.decodeFile(filename, options);
    }

    /**
     * Opens the Android app drawer by swiping up from the bottom of the screen.
     */
    public void openAppDrawer() {
        pressHome();
        swipe(new RelativePosition(0.5, 1.0), new RelativePosition(0.5, 0.5));
    }

    /**
     * Opens the Android notifications.
     */
    public void openNotifications() {
        try {
            if (!uiDevice.openNotification()) {
                Log.println(Log.ERROR, "Device", "Failed to open notifications");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to open notifications: " + e.getMessage());
        }
    }
}
