package ca.cmpt213.as4.model;

/**
 * This class manages each Cell in the Maze.
 */
public class Cell {
    private boolean isRevealed;
    private boolean isVisited;
    private boolean isWall;
    private boolean isTraveled;

    public Cell(boolean isRevealed, boolean isVisited, boolean isWall) {
        this.isRevealed = isRevealed;
        this.isVisited = isVisited;
        this.isWall = isWall;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public void setRevealed(boolean revealed) {
        isRevealed = revealed;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    public boolean isWall() {
        return isWall;
    }

    public void setWall(boolean wall) {
        isWall = wall;
    }

    public boolean isTraveled() {
        return isTraveled;
    }

    public void setTraveled(boolean traveled) {
        isTraveled = traveled;
    }
}
