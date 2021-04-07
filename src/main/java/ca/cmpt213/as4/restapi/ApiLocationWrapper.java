package ca.cmpt213.as4.restapi;

import ca.cmpt213.as4.model.Cat;
import ca.cmpt213.as4.model.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * This wrapper class converts data about the Locations of Cat, Mouse, Cheese from ui to backend and vice versa
 */

public class ApiLocationWrapper {
    public int x;
    public int y;


    // MAY NEED TO CHANGE PARAMETERS HERE TO SUITE YOUR PROJECT
    public static ApiLocationWrapper makeFromCellLocation(Point point, int status) {
        ApiLocationWrapper location = new ApiLocationWrapper();
        if (status == 1) {
            location.x = point.getColumn();
            location.y = point.getRow();
        } else if (status == 2) {
            location.x = point.getColumn();
            location.y = point.getRow();
        } else if (status == 3) {
            location.x = point.getColumn();
            location.y = point.getRow();
        }

        // Populate this object!

        return location;
    }

    // MAY NEED TO CHANGE PARAMETERS HERE TO SUITE YOUR PROJECT
    public static List<ApiLocationWrapper> makeFromCellLocations(List<Cat> cats) {
        List<ApiLocationWrapper> locations = new ArrayList<>();
        for (Cat cat : cats) {
            locations.add(ApiLocationWrapper.makeFromCellLocation(cat.getLocation(), 3));
        }
        // Populate this object!

        return locations;
    }
}
