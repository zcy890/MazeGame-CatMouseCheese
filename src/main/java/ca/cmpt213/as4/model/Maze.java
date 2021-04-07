package ca.cmpt213.as4.model;

import java.util.Arrays;
import java.util.Collections;

/**
 * This class generates a maze of size 20 by 15 and enables client to reveals the routes
 * partially or all at once.
 * The maze is created using backtracking algorithm.
 */
public class Maze {
    private final int DEFAULT_NUM_OF_COLS = 20;
    private final int DEFAULT_NUM_OF_ROWS = 15;
    private final int TOP_BORDER = 0;
    private final int BOTTOM_BORDER = DEFAULT_NUM_OF_ROWS - 1;
    private final int LEFT_BORDER = 0;
    private final int RIGHT_BORDER = DEFAULT_NUM_OF_COLS - 1;
    private Cell[][] grid = new Cell[DEFAULT_NUM_OF_ROWS][DEFAULT_NUM_OF_COLS];

    public Maze() {
        initGridAsCells();
        Point start = new Point(1, 1);
        carvePassagesFrom(start);
        setPartialRevealedAndTraveled(start);
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public int getDefaultNumOfCols() {
        return DEFAULT_NUM_OF_COLS;
    }

    public int getDefaultNumOfRows() {
        return DEFAULT_NUM_OF_ROWS;
    }

    public void setRevealedAndTraveledAll() {
        for (int r = 0; r < DEFAULT_NUM_OF_ROWS; r++) {
            for (int c = 0; c < DEFAULT_NUM_OF_COLS; c++) {
                grid[r][c].setRevealed(true);
                grid[r][c].setTraveled(true);
            }
        }
    }

    public void setPartialRevealedAndTraveled(Point location) {
        int r = location.getRow();
        int c = location.getColumn();
        for (int rOffset = -1; rOffset <= 1; rOffset += 1) {
            if (r + rOffset < TOP_BORDER || r + rOffset > BOTTOM_BORDER) {
                continue;
            }
            for (int cOffset = -1; cOffset <= 1; cOffset += 1) {
                if (c + cOffset < LEFT_BORDER || c + cOffset > RIGHT_BORDER) {
                    continue;
                }
                grid[r + rOffset][c + cOffset].setRevealed(true);
                grid[r + rOffset][c + cOffset].setTraveled(true);
            }
        }
    }

    public void setRevealedOneCell(Point location) {
        grid[location.getRow()][location.getColumn()].setRevealed(true);
    }

    public void setRevealedOneCell(Point prevLocation, Point newLocation, boolean isCheese) {
        int prevR = prevLocation.getRow();
        int prevC = prevLocation.getColumn();
        int newR = newLocation.getRow();
        int newC = newLocation.getColumn();

        if (!grid[prevR][prevC].isTraveled() && !isCheese) {
            grid[prevR][prevC].setRevealed(false);
        }
        grid[newR][newC].setRevealed(true);

    }

    private void initGridAsCells() {
        int topRow = TOP_BORDER + 1;
        int bottomRow = BOTTOM_BORDER - 1;
        int leftmostCol = LEFT_BORDER + 1;
        int rightmostCol = RIGHT_BORDER - 1;
        for (int r = 0; r < DEFAULT_NUM_OF_ROWS; r++) {
            for (int c = 0; c < DEFAULT_NUM_OF_COLS; c++) {
                if ((r == topRow || r == bottomRow) && (c == leftmostCol || c == rightmostCol)) {
                    grid[r][c] = new Cell(false, false, false);
                } else if (r == TOP_BORDER || r == BOTTOM_BORDER || c == LEFT_BORDER || c == RIGHT_BORDER) {
                    grid[r][c] = new Cell(true, false, true);
                } else {
                    grid[r][c] = new Cell(false, false, true);
                }
            }
        }
    }

    private boolean isBorder(Point location) {
        int r = location.getRow();
        int c = location.getColumn();
        return r == TOP_BORDER || r == BOTTOM_BORDER || c == LEFT_BORDER || c == RIGHT_BORDER;
    }

    private void carvePassagesFrom(Point currentPt) {
        int r = currentPt.getRow();
        int c = currentPt.getColumn();

        Direction[] dirs = Direction.values();
        Collections.shuffle(Arrays.asList(dirs));
        for (Direction dir : dirs) {
            Point neighborPt = currentPt.getNeighbor(dir);
            Cell neighborCell = grid[neighborPt.getRow()][neighborPt.getColumn()];

            if (!isBorder(neighborPt) && !neighborCell.isVisited() && !isTwoByTwoConstraintBroken(neighborPt)) {
                grid[r][c].setVisited(true);
                grid[r][c].setWall(false);
                carvePassagesFrom(neighborPt);
            }
        }
    }

    // TODO: Can't seem to get this to work properly. Hmm...
    private boolean isTwoByTwoConstraintBroken(Point location) {
        int r = location.getRow();
        int c = location.getColumn();
        for (int rOffset = -1; rOffset <= 1; rOffset++) {
            if (r + rOffset <= TOP_BORDER || r + rOffset >= BOTTOM_BORDER) {
                continue;
            }
            for (int cOffset = -1; cOffset <= 1; cOffset++) {
                if (c + cOffset <= LEFT_BORDER || c + cOffset >= RIGHT_BORDER
                        || (cOffset == 0 && rOffset == 0)) {
                    continue;
                }
                if (!grid[r + rOffset][c + cOffset].isWall()
                        && !grid[r + rOffset][c].isWall()
                        && !grid[r][c + cOffset].isWall()) {
                    return true;
                }
            }
        }
        return false;
    }

    private void dumpGrid() {
        for (Cell[] row : grid) {
            for (Cell cell : row) {
                if (cell.isWall()) {
                    System.out.print("#");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    /*
    // main function for testing
    public static void main(String[] args) {
        Maze m = new Maze();
        Cell[][] g = m.getGrid();
        // bottom left
        g[2][1].setWall(false);
        g[3][1].setWall(false);
        g[3][2].setWall(false);

         //top left
        g[1][1].setWall(false);
        g[1][2].setWall(false);
        g[2][1].setWall(false);

         // bottom right
        g[3][3].setWall(false);
        g[3][2].setWall(false);
        g[2][3].setWall(false);

         // top right
        g[1][2].setWall(false);
        g[1][3].setWall(false);
        g[2][3].setWall(false);

        if (m.isTwoByTwoConstraintBroken(new Point(2,2))) {
            System.out.println("Test passed!");
        } else {
            System.out.println("Test didn't pass :(");
        }

        m.dumpGrid();
    }
    */
}
