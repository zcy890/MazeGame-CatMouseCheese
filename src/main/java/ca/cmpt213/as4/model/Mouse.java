package ca.cmpt213.as4.model;

/**
 * This class constructs the mouse and get/set the location of the mouse.
 */
public class Mouse {
    private Point location;

    public Mouse(Point point) {
        this.location = point;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }
}
