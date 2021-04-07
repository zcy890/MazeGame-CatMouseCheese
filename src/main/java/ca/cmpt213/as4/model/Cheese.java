package ca.cmpt213.as4.model;

/**
 * This class constructs the cheese and gets the location of the cheese.
 */
public class Cheese {
    private Point location;

    public Cheese(Point location) {
        this.location = location;
    }

    public Point getLocation() {
        return location;
    }

}
