package com.rpa.automationframework;

import android.app.Instrumentation;
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

        for (int i = 0; i < numberOfTries; i++) {
            if (uiDevice.pressBack()) {
                return;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // Ignore.
            }
        }

        throw new RuntimeException("Failed to press back button");
    }

    public void pressBack() {
        pressBack(1);
    }

    public void pressHome(int numberOfTries) {
        if (numberOfTries < 1) {
            throw new IllegalArgumentException("numberOfTries must be greater than 0");
        }

        for (int i = 0; i < numberOfTries; i++) {
            if (uiDevice.pressHome()) {
                return;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // Ignore.
            }
        }

        throw new RuntimeException("Failed to press home button");
    }

    public void pressHome() {
        pressHome(1);
    }

    public void pressRecentApps(int numberOfTries) {
        if (numberOfTries < 1) {
            throw new IllegalArgumentException("numberOfTries must be greater than 0");
        }

        for (int i = 0; i < numberOfTries; i++) {
            try {
                if (uiDevice.pressRecentApps()) {
                    return;
                }

                Thread.sleep(1000);
            } catch (Exception e) {
                // TODO: Log.
            }
        }

        throw new RuntimeException("Failed to press recent apps button");
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
}
