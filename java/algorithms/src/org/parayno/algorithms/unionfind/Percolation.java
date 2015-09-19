
import java.util.Random;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	// create N-by-N grid, with all sites blocked
	private int[][] grid;
	private int N;
	private int openSites = 0;
	private int totalSites = 0;
	private WeightedQuickUnionUF quickUnion;
	private int virtualTopSite = 0;
	private int virtualBottomSite = 0;
	
	public Percolation(int N) {
		if (N <= 0) {
			throw new IndexOutOfBoundsException();
		}		
		this.N = N;
		this.totalSites = N * N;
		grid = new int[N][N];
		for (int j = 0;j < N;j++) {
			for (int i = 0;i < N;i++) {
				grid[j][i] = 0;
			}
		}
		quickUnion = new WeightedQuickUnionUF((N * N) + 2);
		virtualTopSite = (N * N);
		virtualBottomSite = (N * N) + 1;
	}

	// open site (row i, column j) if it is not open already	
	public void open(int j, int i) {
		i--;
		j--;		
		if (i < 0 || i > N - 1) {
			throw new IndexOutOfBoundsException();
		}
		if (j < 0 || j > N - 1) {
			throw new IndexOutOfBoundsException();
		}		
		if (grid[i][j] == 0) {
			openSites++;
			grid[i][j] = 1;
		}
		// Look for site above recently open site
		// at the top of the grid so no sites above
		//System.out.println("checking for site above opened site");
		int site1 = 0;
		int site2 = 0;
		if (j > 0) {
			if (isOpen(j,i+1)) {
				site1 = (j * 10) + i;
				site2 = ((j - 1) * 10) + i;
				/*
				System.out.println("site above " + j + "," + i + " is open");
				System.out.println("site1:" + site1);
				System.out.println("site2:" + site2);
				*/
				quickUnion.union(site1,site2);
			}
		} else {
			// In the top row, union the top open site
			// with the virtualTopSite
			site1 = (j * 10) + i;
			//System.out.println("Union of top row site at " + site1 + " with virtual top site: " + virtualTopSite);
			quickUnion.union(site1,virtualTopSite);
		}
		// Checking site to left of opened site
		// If i == 0 then no site to the left
		if (i > 0) {
			if (isOpen(j+1,i)) {
				site1 = (j * 10) + i;
				site2 = (j * 10) + (i - 1);	
				/*
				System.out.println("site to the left of " + j + "," + i + " is open");
				System.out.println("site1:" + site1);
				System.out.println("site2:" + site2);
				*/
				quickUnion.union(site1,site2);				
			}
		}
		// Checking site to the right of opened site
		// If i == N then no site to the right
		if (i < N - 1) {
			if (isOpen(j+1,i+2)) {
				site1 = ((j * 10) + i);
				site2 = ((j * 10) + (i + 1));
				/*
				System.out.println("site to the right of " + j + "," + i + " is open");
				System.out.println("site1:" + site1);
				System.out.println("site2:" + site2);
				*/
				quickUnion.union(site1,site2);				
			}
		}		
		// Checking site to the bottom of opened site
		// If j == N then no site to the bottom to check
		if (j < N - 1) {
			if (isOpen(j+2,i+1)) {
				site1 = ((j * 10) + i);
				site2 = ((j + 1) * 10) + i;
				/*
				System.out.println("site below " + j + "," + i + " is open");
				System.out.println("site1:" + site1);
				System.out.println("site2:" + site2);
				*/
				quickUnion.union(site1,site2);				
			}
		} else {
			// In the bottom row, union the bottom site
			// with the just opened site
			
			site1 = (j * 10) + i;
			//System.out.println("Union of bottom row site at " + site1 + " with virtual bottom site: " + virtualBottomSite);
			quickUnion.union(site1,virtualBottomSite);			
		}
		
		//printGrid();
	}

	// is site (row i, column j) open?	
	public boolean isOpen(int j, int i) {
		i--;
		j--;
		if (i < 0 || i > N - 1) {
			throw new IndexOutOfBoundsException();
		}
		if (j < 0 || j > N - 1) {
			throw new IndexOutOfBoundsException();
		}
		if (grid[i][j] == 1) {
			return true;
		}
		return false;
	}

	// is site (row i, column j) full?
	public boolean isFull(int j, int i) {
		i--;
		j--;		
		if (i < 0 || i > N - 1) {
			throw new IndexOutOfBoundsException();
		}
		if (j < 0 || j > N - 1) {
			throw new IndexOutOfBoundsException();
		}		
		if (grid[i][j] == 0) {
			return true;
		}
		return false;		
	}

	// does the system percolate?	
	public boolean percolates() {
		if (quickUnion.connected(virtualTopSite, virtualBottomSite)) {
			//System.out.println("Percolates at " + openSites + " open sites out of " + totalSites);
			return true;
		}
		return false;
	}

	// test client (optional)
	public static void main(String[] args) {
		int M = 10;
		Percolation perc = new Percolation(M);
		while (!perc.percolates()) {
			// Usually this can be a field rather than a method variable
		    Random rand = new Random();

		    // nextInt is normally exclusive of the top value,
		    // so add 1 to make it inclusive
		    int min = 1;
			int x = rand.nextInt((M - min) + 1) + min;
			int y = rand.nextInt((M - min) + 1) + min;
			//System.out.println("Opening: " + x + "," + y);
			perc.open(x, y);
			
			//System.out.println("OPEN SITES: " + perc.getOpenSites());
		}
	}

	private void printGrid() {
		for (int j = 0;j < this.N;j++) {
			for (int i = 0;i < this.N;i++) {
				System.out.print(grid[i][j] + " ");
			}
			System.out.println("|");
		}		
	}
	
	public int getOpenSites() {
		return openSites;
	}

	public void setOpenSites(int openSites) {
		this.openSites = openSites;
	}

	public int getTotalSites() {
		return totalSites;
	}

	public void setTotalSites(int totalSites) {
		this.totalSites = totalSites;
	}	
}
