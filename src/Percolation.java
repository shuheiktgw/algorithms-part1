public class Percolation {
    private int[] id;
    private int[] status;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if(n < 1) {
            throw new IllegalArgumentException("You have to specify a number bigger than 1.");
        }

        int size = n * n;
        id = new int[size];
        status = new int[size];

        for(int i = 0; i < (n * n); i++) {
            id[i] = i;
            status[i] = 0;
        }
    }

    public int[] getId() {
        return id;
    }

    public int[] getStatus() {
        return status;
    }

    public    void open(int row, int col) {}    // open site (row, col) if it is not open already
    public boolean isOpen(int row, int col) { return true; }  // is site (row, col) open?
    public boolean isFull(int row, int col) { return true; }  // is site (row, col) full?
    public     int numberOfOpenSites() { return 0; }       // number of open sites
    public boolean percolates() { return true; }              // does the system percolate?

    public static void main(String[] args){}   // test client (optional)
}
