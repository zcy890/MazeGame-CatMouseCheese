package ca.cmpt213.as4.restapi;


import ca.cmpt213.as4.model.GameManager;
import ca.cmpt213.as4.model.Maze;
import ca.cmpt213.as4.model.Point;

import java.util.List;

/**
 * This wrapper class converts data about the board from ui to backend and vice versa
 */

public class ApiBoardWrapper {
    public int boardWidth;
    public int boardHeight;
    public ApiLocationWrapper mouseLocation;
    public ApiLocationWrapper cheeseLocation;
    public List<ApiLocationWrapper> catLocations;
    public boolean[][] hasWalls;
    public boolean[][] isVisible;

    // MAY NEED TO CHANGE PARAMETERS HERE TO SUITE YOUR PROJECT
    public static ApiBoardWrapper makeFromGame(GameManager manager) {
        ApiBoardWrapper wrapper = new ApiBoardWrapper();
        wrapper.setBoardHeight(manager.getMaze().getDefaultNumOfRows());
        wrapper.setBoardWidth(manager.getMaze().getDefaultNumOfCols());
        wrapper.setMouseLocation(getMouseLocation(manager, 1));
        wrapper.setCheeseLocation(getCheeseLocation(manager, 2));
        wrapper.setCatLocations(getCatsLocation(manager));
        wrapper.setHasWalls(getHasWall(manager.getMaze()));
        wrapper.setIsVisible(getIsVisible(manager.getMaze()));
        // Populate this object!

        return wrapper;
    }

    public static ApiLocationWrapper getCheeseLocation(GameManager manager, int status) {
        Point cheeseLocation;
        cheeseLocation = manager.getCheese().getLocation();
        return ApiLocationWrapper.makeFromCellLocation(cheeseLocation, status);
    }

    public static List<ApiLocationWrapper> getCatsLocation(GameManager manager) {
        return ApiLocationWrapper.makeFromCellLocations(manager.getCats());
    }

    public static ApiLocationWrapper getMouseLocation(GameManager manager, int status) {
        Point mouseLocation;
        mouseLocation = manager.getMouse().getLocation();
        return ApiLocationWrapper.makeFromCellLocation(mouseLocation, status);
    }

    public static boolean[][] getHasWall(Maze maze) {
        boolean[][] hasWallMaze = new boolean[maze.getDefaultNumOfRows()][maze.getDefaultNumOfCols()];

        for (int r = 0; r < maze.getDefaultNumOfRows(); r++) {
            for (int c = 0; c < maze.getDefaultNumOfCols(); c++) {
                hasWallMaze[r][c] = maze.getGrid()[r][c].isWall();
            }
        }
        return hasWallMaze;
    }

    public static boolean[][] getIsVisible(Maze maze) {
        boolean[][] isVisibleMaze = new boolean[maze.getDefaultNumOfRows()][maze.getDefaultNumOfCols()];

        for (int r = 0; r < maze.getDefaultNumOfRows(); r++) {
            for (int c = 0; c < maze.getDefaultNumOfCols(); c++) {
                isVisibleMaze[r][c] = maze.getGrid()[r][c].isRevealed();
            }
        }
        return isVisibleMaze;
    }

    public void setCheeseLocation(ApiLocationWrapper location) {
        this.cheeseLocation = location;
    }

    public void setCatLocations(List<ApiLocationWrapper> locations) {
        this.catLocations = locations;
    }

    public void setMouseLocation(ApiLocationWrapper location) {
        this.mouseLocation = location;
    }

    public void setBoardWidth(int boardWidth) {
        this.boardWidth = boardWidth;
    }

    public void setBoardHeight(int boardHeight) {
        this.boardHeight = boardHeight;
    }

    public void setHasWalls(boolean[][] hasWalls) {
        this.hasWalls = hasWalls;
    }

    public void setIsVisible(boolean[][] isVisible) {
        this.isVisible = isVisible;
    }
}
