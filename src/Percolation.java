import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Percolation {
    private int initialSize;
    private int[] id;
    private int[] size;
    private int[] status;

    // create n-by-n grid, with all sites blocked
    Percolation(int n) {
        if(n < 1) {
            throwIllegalNumberException("n " + String.valueOf(n));
        }

        initialSize = n;

        // Need extra two spaces for the top node and the bottom node
        int area = n * n + 2;
        id = new int[area];
        size = new int[area];
        status = new int[area];

        for(int i = 0; i < area; i++) {
            id[i] = i;
            size[i] = 1;
            status[i] = 0;
        }

        // Connect top/bottom node with top/bottom rows
        int lastRow = n * (n - 1);
        for(int i = 1; i <= n; i++) {
            union(0, i);
            union(area - 1, lastRow + i);
        }
    }

    public int[] getId() {
        return id;
    }

    public int[] getSize() {
        return size;
    }

    public int[] getStatus() {
        return status;
    }

    private void setStatus(int row, int col) {
        status[coord2index(row, col)] = 1;
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        setStatus(row, col);
        for (int[] coordinate : adjacent(row, col)) {
            if (isOpen(coordinate[0], coordinate[1])) {
                int p = coord2index(row, col);
                int q = coord2index(coordinate[0], coordinate[1]);
                union(p, q);
            }
        }
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        int idx = coord2index(row, col);

        checkIndex(idx);
        return getStatus()[idx] == 1;
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        int idx = coord2index(row, col);
        return 0 == root(idx) && isOpen(row, col);
    }

    // number of open sites
    public int numberOfOpenSites() {
        int count = 0;
        for (int s : status) {
            if (s == 1) {
                count += 1;
            }
        }

        return count;
    }

    // does the system percolate?
    public boolean percolates() {
        return id[0] == id[initialSize * initialSize + 1];
    }

    private int root(int i) {
        while (i != id[i]) {
            id[i] = id[id[i]];
            i = id[i];
        }

        return i;
    }

    private void union(int p, int q) {
        int i = root(p);
        int j = root(q);

        if (i == j) {
            return;
        }

        if (size[i] < size[j]) {
            id[i] = j;
            size[j] += size[i];
        } else {
            id[j] = i;
            size[i] += size[j];
        }
    }

    private int coord2index(int row, int col) {
        checkRowAndCol(row, col);
        return (row - 1) * initialSize + col;
    }

    private List<int[]> adjacent(int row, int col) {
        checkRowAndCol(row, col);

        List<int[]> adjacent = new ArrayList<>();

        // Not the top
        if (row > 1) {
            adjacent.add(setCoordinate(row - 1, col));
        }

        // Not the bottom
        if (row < initialSize) {
            adjacent.add(setCoordinate(row + 1, col));
        }

        // Not the left most
        if (col != 1) {
            adjacent.add(setCoordinate(row, col - 1));
        }

        // Not the right most
        if (col != initialSize) {
            adjacent.add(setCoordinate(row, col + 1));
        }

        return adjacent;
    }

    private int[] setCoordinate(int row, int col) {
        int[] coordinate = new int[2];
        coordinate[0] = row;
        coordinate[1] = col;
        return coordinate;
    }

    private void checkRowAndCol(int row, int col) {
        if(row < 1 || col < 1 || row > initialSize || col > initialSize) {
            throwIllegalNumberException("row " + String.valueOf(row) + ", col " + String.valueOf(col));
        }
    }

    private void checkIndex(int i) {
        if(i < 1 || i > initialSize * initialSize) {
            throwIllegalNumberException("i " + String.valueOf(i));
        }
    }

    private void throwIllegalNumberException(String got) {
        throw new IllegalArgumentException("You have to specify a number bigger than 1. got: " + got);
    }

}
