package com.rpa.automationframework.internal.types;

import com.rpa.automationframework.Device;

public class AbsoluteCoordinates implements Position {
    private final int x;
    private final int y;

    public AbsoluteCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        if (x < 0 || x > Device.getInstance().getDisplayWidth()) {
            throw new IllegalArgumentException("x must be between 0 and " + Device.getInstance().getDisplayWidth());
        }

        return x;
    }

    public int getY() {
        if (y < 0 || y > Device.getInstance().getDisplayHeight()) {
            throw new IllegalArgumentException("y must be between 0 and " + Device.getInstance().getDisplayHeight());
        }

        return y;
    }
}
