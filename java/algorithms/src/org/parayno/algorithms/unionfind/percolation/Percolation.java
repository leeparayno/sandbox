package org.parayno.algorithms.unionfind.percolation;

import java.util.Random;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // create N-by-N grid, with all sites blocked
    private int[][] grid;
    private int N;
    // private int openSites = 0;
    // private int totalSites = 0;
    private WeightedQuickUnionUF virtualQU;
    private WeightedQuickUnionUF domainQU;
    private int virtualTopSite = 0;
    private int virtualBottomSite = 0;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IndexOutOfBoundsException("N must be a positive number. Given:" + N);
        }
        this.N = N;
        // this.totalSites = N * N;
        grid = new int[N][N];
        for (int j = 0; j < N; j++) {
            for (int i = 0; i < N; i++) {
                grid[j][i] = 0;
            }
        }
        virtualQU = new WeightedQuickUnionUF((N * N) + 2);
        domainQU = new WeightedQuickUnionUF((N * N) + 1);
        virtualTopSite = (N * N);
        virtualBottomSite = (N * N) + 1;
    }

    // open site (row i, column j) if it is not open already
    public void open(int j, int i) {
        int y = j - 1;
        int x = i - 1;

        if (x < 0 || (x > N - 1 && x != 0)) {
            throw new IndexOutOfBoundsException();
        }
        if (y < 0 || (y > N - 1 && x != 0)) {
            throw new IndexOutOfBoundsException();
        }

        if (grid[x][y] == 0) {
            // openSites++;
            grid[x][y] = 1;
        } else {
            // Previously opened
            return;
        }

        int openedSite = xyTo1D(j, i, N);
        int site2 = 0;

        // System.out.println("Opened site (original coords):" + j + "," + i + "; 1D:" + openedSite);

        // at the top of the grid so no sites above
        if (j == 1) {
            if (!virtualQU.connected(openedSite, virtualTopSite)) {
                domainQU.union(openedSite, virtualTopSite);
                virtualQU.union(openedSite, virtualTopSite);
            }
        } else {
            // Look for site above recently open site
            if (isOpen(j - 1, i)) {
                // openedSite = (y * 10) + x;
                // site2 = ((y - 1) * 10) + x;
                site2 = xyTo1D(j - 1, i, N);
                // System.out.println("Above site (original coords):" + (j - 1) + "," + i + "; 1D:" + site2);
                /*
                 * System.out.println("site above " + j + "," + i + " is open"); System.out.println("site1:" + site1);
                 * System.out.println("site2:" + site2);
                 */
                // if (!virtualQU.connected(openedSite, site2)) {
                // if (y == (N - 1)) {
                // Last row, don't try to connect the whole tree
                // return;
                // }
                domainQU.union(openedSite, site2);
                virtualQU.union(openedSite, site2);
                // }
            }
        }
        // Checking site to left of opened site
        // If i == 0 then no site to the left
        if (x > 0) {
            if (isOpen(j, i - 1)) {
                // openedSite = (y * 10) + x;
                // site2 = (y * 10) + (x - 1);
                site2 = xyTo1D(j, i - 1, N);
                // System.out.println("Left site (original coords):" + j + "," + (i - 1) + "; 1D:" + site2);
                /*
                 * System.out.println("site to the left of " + j + "," + i + " is open"); System.out.println("site1:" + site1);
                 * System.out.println("site2:" + site2);
                 */
                // if (!virtualQU.connected(openedSite, site2)) {
                // if (y == (N - 1)) {
                // Last row, don't try to connect the whole tree
                // return;
                // }
                domainQU.union(openedSite, site2);
                virtualQU.union(openedSite, site2);
                // }
            }
        }
        // Checking site to the right of opened site
        // If i == N then no site to the right
        if (x < N - 1) {
            if (isOpen(j, i + 1)) {
                // openedSite = ((y * 10) + x);
                // site2 = ((y * 10) + (x + 1));
                site2 = xyTo1D(j, i + 1, N);
                // System.out.println("Right site (original coords):" + j + "," + (i + 1) + "; 1D:" + site2);
                /*
                 * System.out.println("site to the right of " + j + "," + i + " is open"); System.out.println("site1:" + site1);
                 * System.out.println("site2:" + site2);
                 */
                // if (!virtualQU.connected(openedSite, site2)) {
                // if (y == (N - 1)) {
                // Last row, don't try to connect the whole tree
                // return;
                // }
                domainQU.union(openedSite, site2);
                virtualQU.union(openedSite, site2);
                // }
            }
        }
        // Checking site to the bottom of opened site
        // If j == N then no site to the bottom to check
        if (y < N - 1) {
            if (isOpen(j + 1, i)) {
                // openedSite = ((y * 10) + x);
                // site2 = ((y + 1) * 10) + x;
                site2 = xyTo1D(j + 1, i, N);
                // System.out.println("Bottom site (original coords):" + (j + 1) + "," + i + "; 1D:" + site2);
                /*
                 * System.out.println("site below " + j + "," + i + " is open"); System.out.println("site1:" + site1);
                 * System.out.println("site2:" + site2);
                 */
                // System.out.println("Union of bottom row site at " + openedSite + " with bottom site: " + site2);
                // System.out.println("virtualQU connected: " + virtualQU.connected(openedSite, site2));
                // System.out.println("domainQU connected: " + domainQU.connected(openedSite, site2));
                // if (!virtualQU.connected(openedSite, site2)) {
                // if (y == (N - 1)) {
                // Last row, don't try to connect the whole tree
                // return;
                // }
                domainQU.union(openedSite, site2);
                virtualQU.union(openedSite, site2);

                // }
            }
        } else {
            // In the bottom row, union the bottom site
            // with the just opened site

            // System.out.println("Union of bottom row site at " + openedSite + " with
            // virtual bottom site: " + virtualBottomSite);
            // if (!virtualQU.connected(openedSite, virtualBottomSite)) {
            // if (y == (N - 1)) {
            // Last row, don't try to connect the whole tree
            // return;
            // }
            virtualQU.union(openedSite, virtualBottomSite);
            // }
        }

        // printGrid();
    }

    // is site (row i, column j) open?
    public boolean isOpen(int j, int i) {
        int x = i - 1;
        int y = j - 1;
        if (x < 0 || (x > N - 1 && x != 0)) {
            throw new IndexOutOfBoundsException();
        }
        if (y < 0 || (y > N - 1 && x != 0)) {
            throw new IndexOutOfBoundsException();
        }
        if (grid[x][y] == 1) {
            return true;
        }
        return false;
    }

    // is site (row i, column j) full?
    public boolean isFull(int j, int i) {
        int x = i - 1;
        int y = j - 1;
        if (x < 0 || (x > N - 1 && x != 0)) {
            throw new IndexOutOfBoundsException();
        }
        if (y < 0 || (y > N - 1 && x != 0)) {
            throw new IndexOutOfBoundsException();
        }
        // Is this site connected to the virtualTopSite
        // if (quickUnion.connected(virtualTopSite, (y * 10) + x)) {

        // }
        return domainQU.connected(virtualTopSite, xyTo1D(j, i, N));
    }

    // does the system percolate?
    public boolean percolates() {
        if (virtualQU.connected(virtualTopSite, virtualBottomSite)) {
            // System.out.println("Percolates at " + openSites + " open sites out of " + totalSites);
            return true;
        }
        return false;
    }

    private int xyTo1D(int y, int x, int size) {
        return (((y - 1) * size) + x) - 1;
    }

    // test client (optional)
    public static void main(String[] args) {
        // Testing xyTo1D
        /*
         * System.out.println("xyTo1D(1,1,1):" + xyTo1D(1, 1, 1)); System.out.println("xyTo1D(1,2,2):" + xyTo1D(1, 2, 2));
         * System.out.println("xyTo1D(1,2,2):" + xyTo1D(1, 2, 2)); System.out.println("xyTo1D(2,1,2):" + xyTo1D(2, 1, 2));
         * System.out.println("xyTo1D(2,2,2):" + xyTo1D(2, 2, 2)); System.out.println("xyTo1D(2,4,8):" + xyTo1D(2, 4, 8));
         * System.out.println("xyTo1D(10,1,10):" + xyTo1D(10, 1, 10));
         */
        // Test of input
        int M = 1;
        Percolation perc = new Percolation(M);
        System.out.println("isFull(1,1): " + perc.isFull(1, 1));
        perc.open(1, 1);
        System.out.println("isOpen(1,1): " + perc.isOpen(1, 1));

        M = 0;
        try {
            perc = new Percolation(M);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        M = -1;
        try {
            perc = new Percolation(M);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        M = -10;
        try {
            perc = new Percolation(M);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        M = 10;
        perc = new Percolation(M);
        int openSites = 0;
        while (!perc.percolates()) {
            // Usually this can be a field rather than a method variable
            Random rand = new Random();

            // nextInt is normally exclusive of the top value,
            // so add 1 to make it inclusive
            int min = 1;
            int x = rand.nextInt((M - min) + 1) + min;
            int y = rand.nextInt((M - min) + 1) + min;
            // System.out.println("Opening: " + x + "," + y);
            if (!perc.isOpen(x, y)) {
                perc.open(x, y);
                openSites++;
            }

            System.out.println("OPEN SITES: " + openSites);
        }
    }
    /*
     * private void printGrid() { for (int j = 0; j < this.N; j++) { for (int i = 0; i < this.N; i++) { if
     * (domainQU.connected(virtualTopSite, xyTo1D(j + 1, i + 1, N))) { System.out.print("* "); } else {
     * System.out.print(grid[i][j] + " "); }
     * 
     * } System.out.println("|"); } }
     */
}