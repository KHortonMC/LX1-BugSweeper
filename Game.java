import java.util.Scanner;

class Game {
    Grid grid;
    int bugCount;

    public Game(int rows, int cols, int BugCount) {
        this.grid = new Grid(rows, cols);
        this.bugCount = BugCount;
    }

    public void start() {
        grid.placeBugs(bugCount);
        grid.calculateNeighborBugCounts();
        play();
    }

    private void play() {
        Scanner scanner = new Scanner(System.in);
        boolean gameOver = false;

        while (!gameOver) {
            grid.printGrid(true);
            System.out.println("Enter row and column to reveal (e.g., '1 2'):");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (!grid.isInBounds(row, col)) {
                System.out.println("Invalid coordinates. Try again.");
                continue;
            }

            grid.revealCell(row, col);

            if (grid.cells[row][col].isBug) {
                System.out.println("Boom! You hit a bug. Game over.");
                grid.printGrid(true);
                gameOver = true;
            } else if (allCellsRevealed()) {
                System.out.println("Congratulations! You revealed all safe cells!");
                grid.printGrid(true);
                gameOver = true;
            }
        }

        scanner.close();
    }

    private boolean allCellsRevealed() {
        for (int i = 0; i < grid.rows; i++) {
            for (int j = 0; j < grid.cols; j++) {
                if (!grid.cells[i][j].isBug && !grid.cells[i][j].isRevealed) {
                    return false;
                }
            }
        }
        return true;
    }
}