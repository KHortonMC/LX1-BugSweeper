class Cell {
    boolean isBug;
    boolean isRevealed;
    int neighborBugCount;

    public Cell() {
        this.isBug = false;
        this.isRevealed = false;
        this.neighborBugCount = 0;
    }
}