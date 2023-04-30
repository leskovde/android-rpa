package com.rpa.automationframework;

import android.app.Instrumentation;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;

import com.rpa.automationframework.internal.finder.BySelectorControlFinder;
import com.rpa.automationframework.internal.finder.ControlFinder;
import com.rpa.automationframework.internal.finder.UiAutomatorControlFinder;
import com.rpa.automationframework.internal.finder.UiSelectorControlFinder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class Device {
    private UiDevice uiDevice;
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
}
