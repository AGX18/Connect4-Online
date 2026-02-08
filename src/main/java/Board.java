enum Disc {
    RED,
    YELLOW,
}

public class Board {
    final int rows = 6;
    final int cols = 7;
    Disc[][] grid;

    public Board() {
        this.grid = new Disc[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.grid[i][j] = null;
            }
        }
    }

    private boolean canPlace(int column) {
        return this.grid[0][column] == null; // last row is empty -> can place
    }

    int placeDisc(int column, Disc color) {
        for (int i = rows - 1; i >= 0; i--) {
            if (this.grid[i][column] == null) {
                this.grid[i][column] = color;
                return i;
            }
        }
        return -1; // can't place the disc
    }

    public boolean isFull() {
        for (int i = 0; i < cols; i++) {
            if (this.grid[0][i] == null) {
                return false;
            }
        }
        return true;
    }

//    checkWin(row, column, color) -> bool

    private int countInDirection(int row, int column,int dr,int dc, Disc color) {
        int count = 0;
        row += dr;
        column += dc;
        while (inBounds(row, column) && grid[row][column] == color) {
            count++;
            row += dr;
            column += dc;
        }
        return count;
    }

    private boolean inBounds(int row, int column) {
        return column >= 0 && column < cols && row >= 0 && row < rows;
    }

    public boolean checkWin(int row, int col, Disc color) {
        int[][] directions = {{0, 1}, {1, 0}, {1, 1}, {-1, 1}};

        for  (int[] direction : directions) {
            int count = 1;
            count += countInDirection(row, col, direction[0], direction[1], color);
            count += countInDirection(row, col, direction[0] * -1, direction[1] * -1, color);
            if  (count >= 4) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (this.grid[i][j] == null) {
                    sb.append("---");
                } else if (this.grid[i][j] == Disc.RED) {
                    sb.append("RED");
                } else if (this.grid[i][j] == Disc.YELLOW) {
                    sb.append("YEW");
                }
                sb.append(" | ");
            }
            sb.append("\n");
            sb.append("\n");
        }
        return sb.toString();
    }
}


