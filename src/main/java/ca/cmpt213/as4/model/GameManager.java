package ca.cmpt213.as4.model;

import java.util.*;

/**
 * This class acts as the middle man between the UI and the underlying implementations.
 * It has winning/losing condition checking methods, enables the mouse and the cats movement,
 * and places cheese randomly.
 */
public class GameManager {
    private final String ERROR_WALL = "Invalid move: you cannot move through walls!";
    private final int DEFAULT_NUM_OF_CHEESE = 5;
    private int numOfCheeseToBeCollected = DEFAULT_NUM_OF_CHEESE;
    private int numOfCheeseCollected = 0;
    private long id;
    private boolean success;
    private Maze maze;
    private Mouse mouse;
    private ArrayList<Cat> cats = new ArrayList<>();
    private Cheese cheese;

    public GameManager() {
        this.maze = new Maze();
        this.mouse = new Mouse(new Point(1, 1));
        this.cheese = createCheese();
        createCats();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Maze getMaze() {
        return maze;
    }

    public Cheese getCheese() {
        return cheese;
    }

    public int getNumOfCheeseToBeCollected() {
        return numOfCheeseToBeCollected;
    }

    public int getNumOfCheeseCollected() {
        return numOfCheeseCollected;
    }

    public Mouse getMouse() {
        return mouse;
    }

    public List<Cat> getCats() {
        return cats;
    }

    public boolean isCatHere(Point location) {
        for (Cat cat : cats) {
            if (cat.getLocation().equals(location)) {
                return true;
            }
        }
        return false;
    }

    public void moveMouse(Direction dir) throws MazeGameException {
        Point currLocation = mouse.getLocation();
        Point newLocation = currLocation.getNeighbor(dir);
        if (isLocationValid(newLocation)) {
            mouse.setLocation(newLocation);
            checkCheeseIsCollected();
            maze.setPartialRevealedAndTraveled(mouse.getLocation());
        } else {
            throw new MazeGameException();
        }
    }

    public void moveCats() {
        Direction[] dirs = Direction.values();

        for (Cat cat : cats) {
            Collections.shuffle(Arrays.asList(dirs));
            Direction dir = dirs[0];
            Point currLocation = cat.getLocation();
            Point newLocation = currLocation.getNeighbor(dir);

            if (isLocationValid(newLocation)) {
                maze.setRevealedOneCell(cat.getLocation(), newLocation,
                        cheese.getLocation().equals(currLocation));
                cat.setLocation(newLocation);
            }
        }
    }

    public void moveMouseOrCat(String direction) throws MazeGameException {
        switch (direction) {
            case "MOVE_UP":
                moveMouse(Direction.UP);
                moveCats();
                break;
            case "MOVE_DOWN":
                moveMouse(Direction.DOWN);
                moveCats();
                break;
            case "MOVE_LEFT":
                moveMouse(Direction.LEFT);
                moveCats();
                break;
            case "MOVE_RIGHT":
                moveMouse(Direction.RIGHT);
                moveCats();
                break;
            case "MOVE_CATS":
                moveCats();
                break;
            default:
                break;
        }
    }

    public void activateCheatState(String state) {
        switch (state) {
            case "1_CHEESE":
                activateCheatCheese();
                break;
            case "SHOW_ALL":
                activateCheatMap();
                break;
            default:
                break;
        }
    }

    public boolean isWon() {
        return numOfCheeseCollected >= numOfCheeseToBeCollected;
    }

    public boolean isLost() {
        return checkIfCatEatsMouse();
    }

    public void activateCheatCheese() {
        numOfCheeseToBeCollected = 1;
    }

    public void activateCheatMap() {
        maze.setRevealedAndTraveledAll();
    }

    private void checkCheeseIsCollected() {
        if (mouse.getLocation().equals(cheese.getLocation())) {
            if (numOfCheeseCollected < numOfCheeseToBeCollected) {
                cheese = createCheese();
                numOfCheeseCollected += 1;
            }
        }
    }

    private boolean checkIfCatEatsMouse() {
        for (Cat cat : cats) {
            if (cat.getLocation().equals(mouse.getLocation())) {
                return true;
            }
        }
        return false;
    }

    private void createCats() {
        int topBorder = 0;
        int rightBorder = maze.getDefaultNumOfCols() - 1;
        int leftBorder = 0;
        int bottomBorder = maze.getDefaultNumOfRows() - 1;
        ArrayList<Point> catLocation = new ArrayList<>();
        catLocation.add(new Point(topBorder + 1, rightBorder - 1));
        catLocation.add(new Point(bottomBorder - 1, rightBorder - 1));
        catLocation.add(new Point(bottomBorder - 1, leftBorder + 1));

        for (Point point : catLocation) {
            cats.add(new Cat(point));
            maze.setRevealedOneCell(point);
        }
    }

    private Cheese createCheese() {
        int r, c;
        Random rand = new Random();
        Point location;
        do {
            r = rand.nextInt(maze.getDefaultNumOfRows() - 1);
            c = rand.nextInt(maze.getDefaultNumOfCols() - 1);
            location = new Point(r, c);
        } while (maze.getGrid()[r][c].isWall()
                || mouse.getLocation().equals(location));

        if (cheese == null || cheese.getLocation() == null) {
            maze.setRevealedOneCell(location);
        } else {
            maze.setRevealedOneCell(cheese.getLocation(), location, true);
        }

        return new Cheese(location);
    }

    private boolean isLocationValid(Point location) {
        int r = location.getRow();
        int c = location.getColumn();
        return !maze.getGrid()[r][c].isWall();
    }
}
