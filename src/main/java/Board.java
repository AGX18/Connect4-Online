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

    public boolean checkWin(int row, int col, Disc color) {
        // check horizontally
        int horizontalDics = 0;
        int newCol = col;
        // go right
        while (newCol >= 0 && newCol < cols && this.grid[row][newCol] == color) {
            horizontalDics++;
            newCol++;
        }

        // go left
        newCol = col - 1;
        while (newCol >= 0 && newCol < cols && this.grid[row][newCol] == color) {
            horizontalDics++;
            newCol--;
        }

        // ---------------------------------------------------------------
        // check diagonally
        int diagonalDics = 0;
        newCol = col;
        int newRow = row;
        while (newCol >= 0 && newCol < cols && newRow >= 0 && newRow < rows && this.grid[newRow][newCol] == color) {
            diagonalDics++;
            newCol++;
            newRow++;
        }

        newCol = col - 1;
        newRow = row - 1;
        while (newCol >= 0 && newCol < cols && newRow >= 0 && newRow < rows && this.grid[newRow][newCol] == color) {
            diagonalDics++;
            newCol--;
            newRow--;
        }

        // ---------------------------------------------------------------
        // check vertically
        int verticalDics = 0;
        newRow = row;
        // go down
        while (newRow >= 0 && newRow < rows && this.grid[newRow][col] == color) {
            verticalDics++;
            newRow++;
        }

        // go up
        newRow = col - 1;
        while (newRow >= 0 && newRow < rows && this.grid[newRow][col] == color) {
            verticalDics++;
            newRow--;
        }

        return  horizontalDics >= 4 || diagonalDics >= 4 || verticalDics >= 4;
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


