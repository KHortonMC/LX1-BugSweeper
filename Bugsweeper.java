public class Bugsweeper {
    public static void main(String[] args) {
        final int rows = 5;
        final int cols = 3;
        final int bugs = 8;

        Game game = new Game(rows, cols, bugs);
        game.start();
    }
}