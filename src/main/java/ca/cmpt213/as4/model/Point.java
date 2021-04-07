package ca.cmpt213.as4.model;

/**
 * The class encapsulates a location on a 2D array.
 */
public class Point {
    private int row;
    private int column;

    public Point(int row, int column) {
        assert row >= 0 && column >= 0;
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Point getNeighbor(Direction dir) {
        int r = row;
        int c = column;
        switch (dir) {
            case UP:
                r -= 1;
                break;
            case DOWN:
                r += 1;
                break;
            case RIGHT:
                c += 1;
                break;
            case LEFT:
                c -= 1;
                break;
            default:
                assert false;
        }
        return new Point(r, c);
    }

    @Override
    public String toString() {
        return "Point{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return row == point.row &&
                column == point.column;
    }
}
