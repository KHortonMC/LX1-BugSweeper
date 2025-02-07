import java.util.Random;

class Grid {
    Cell[][] cells;
    int rows;
    int cols;

    public Grid(int rows, int cols) { 
        this.rows = rows;
        this.cols = cols;
        this.cells = new Cell[cols][rows]; // BUG #1: We have cols outside of rows here
        initializeGrid();
    }

    private void initializeGrid() { 
        for (int i = 0; i < rows; i++) { // BUG #1: But we search rows outside of cols here
            for (int j = 0; j < cols; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    public void placeBugs(int bugCount) {
        Random random = new Random();
        int placedBugs = 0;
        while (placedBugs < bugCount) { // BUG #2: we're seeing inconsistent bugs
            int row = random.nextInt(rows);
            int col = random.nextInt(cols);
            cells[row][col].isBug = true;
            placedBugs++;
        }
    }

    public void calculateNeighborBugCounts() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!cells[i][j].isBug) {
                    cells[i][j].neighborBugCount = countNeighborBugs(i, j);
                }
            }
        }
    }

    private int countNeighborBugs(int row, int col) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newRow = row + i;
                int newCol = col + j;
                if (isInBounds(newRow, newCol) && cells[newRow][newCol].isBug) {
                    count++;
                }
            }
        }
        return count;
    }

    public boolean isInBounds(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    public void revealCell(int row, int col) {
        if (!isInBounds(row, col) || cells[row][col].isRevealed) {
            return;
        }
        cells[row][col].isRevealed = true;

        // Hacker Challenge: how can you inject a bug into this code to create stack overflow?
        if (cells[row][col].neighborBugCount == 0 && !cells[row][col].isBug) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    int newRow = row + i;
                    int newCol = col + j;
                    if (isInBounds(newRow, newCol) && !cells[newRow][newCol].isRevealed) {
                        revealCell(newRow, newCol);
                    }
                }
            }
        }
    }

    public void printGrid(boolean revealBugs) { // BUG #3: we seem to always be drawing bugs!
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (cells[i][j].isRevealed) {
                    System.out.print(cells[i][j].isBug ? "*" : cells[i][j].neighborBugCount);
                } else if (revealBugs && cells[i][j].isBug) {
                    System.out.print("*");
                } else {
                    System.out.print(".");
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}