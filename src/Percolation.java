public class Percolation {
    private int size;
    private int[] id;
    private int[] status;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if(n < 1) {
            throwIllegalNumberException();
        }

        size = n;

        // Need extra two spaces for the top node and the bottom node
        int area = n * n + 2;
        id = new int[area];
        status = new int[area];

        for(int i = 0; i < area; i++) {
            id[i] = i;
            status[i] = 0;
        }

        // TODO: Connect the top/bottom nodes with the top/bottom row
    }

    public int[] getId() {
        return id;
    }

    public int[] getStatus() {
        return status;
    }

    private void setStatus(int idx) {
        status[idx] = 1;
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        checkRowAndCol(row, col);
        setStatus(coord2index(row, col));
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        checkRowAndCol(row, col);

        int idx = coord2index(row, col);
        return getStatus()[idx] == 1;
    }
    public boolean isFull(int row, int col) { return true; }  // is site (row, col) full?
    public     int numberOfOpenSites() { return 0; }       // number of open sites
    public boolean percolates() { return true; }              // does the system percolate?

    private int coord2index(int row, int col) {
        checkRowAndCol(row, col);
        return (row - 1) * size + (col - 1);
    }

    private void checkRowAndCol(int row, int col) {
        if(row < 1 || col < 1) {
            throwIllegalNumberException();
        }
    }

    private void throwIllegalNumberException() {
        throw new IllegalArgumentException("You have to specify a number bigger than 1.");
    }

}
