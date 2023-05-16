package com.rpa.automationframework.internal.types;

import com.rpa.automationframework.Device;

/**
 * Represents a position on the screen in relative coordinates,
 * where 0 is the left/top and 1 is the right/bottom.
 */
public class RelativePosition implements Position {
    private final double x;
    private final double y;

    public RelativePosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        if (x < 0 || x > 1) {
            throw new IllegalArgumentException("x must be between 0 and 1");
        }

        return (int)(x * Device.getInstance().getDisplayWidth());
    }

    public int getY() {
        if (y < 0 || y > 1) {
            throw new IllegalArgumentException("y must be between 0 and 1");
        }

        return (int)(y * Device.getInstance().getDisplayHeight());
    }
}
