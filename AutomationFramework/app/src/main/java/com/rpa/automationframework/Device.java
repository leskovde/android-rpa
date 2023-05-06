package com.rpa.automationframework;

import android.app.Instrumentation;
import android.os.RemoteException;
import android.util.Log;
import android.view.KeyEvent;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;

import com.rpa.automationframework.finders.BySelectorControlFinder;
import com.rpa.automationframework.finders.ControlFinder;
import com.rpa.automationframework.finders.UiSelectorControlFinder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Device {
    private final UiDevice uiDevice;
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

    public void pressBack() {
        pressBack(1);
    }

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

    public void pressHome() {
        uiDevice.pressHome();
    }

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

    public void pressRecentApps() {
        pressRecentApps(1);
    }

    public void pressVolumeUp() {
        uiDevice.pressKeyCode(KeyEvent.KEYCODE_VOLUME_UP);
    }

    public void pressVolumeDown() {
        uiDevice.pressKeyCode(KeyEvent.KEYCODE_VOLUME_DOWN);
    }

    public void pressPower() {
        uiDevice.pressKeyCode(KeyEvent.KEYCODE_POWER);
    }

    public void lockScreen() {
        try {
            if (!uiDevice.isScreenOn()) {
                return;
            }
        } catch (RemoteException e) {
            throw new RuntimeException("Failed to check if screen is on: " + e.getMessage());
        }

        pressPower();
    }

    public void unlockScreen() {
        try {
            if (uiDevice.isScreenOn()) {
                return;
            }
        } catch (RemoteException e) {
            throw new RuntimeException("Failed to check if screen is on: " + e.getMessage());
        }

        pressPower();
    }
}
