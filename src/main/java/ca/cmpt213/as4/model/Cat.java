package ca.cmpt213.as4.model;

/**
 * This class constructs the cat and get/set the location of the cat.
 */
public class Cat {
    private Point location;

    public Cat(Point point) {
        this.location = point;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }
}
