package org.parayno.algorithms.unionfind.percolation;

import java.util.Random;

public class PercolationStats {
	private int sum = 0;
	private int iterations = 0;
	private int[] percolations;
	
	// perform T independent experiments on an N-by-N grid	
	public PercolationStats(int N, int T) {
		percolations = new int[T];
		for (int i = 0; i < T - 1; i++) {
			Percolation perc = new Percolation(N);
			while (!perc.percolates()) {
				// Usually this can be a field rather than a method variable
			    Random rand = new Random();

			    // nextInt is normally exclusive of the top value,
			    // so add 1 to make it inclusive
			    int min = 1;
				int x = rand.nextInt((N - min) + 1) + min;
				int y = rand.nextInt((N - min) + 1) + min;
				//System.out.println("Opening: " + x + "," + y);
				perc.open(x, y);
				
				//System.out.println("OPEN SITES: " + perc.getOpenSites());
				
			}	
			percolations[i] = perc.getOpenSites();
			sum += perc.getOpenSites();
		}
		iterations = T;
	}
	
	
	// sample mean of percolation threshold
	public double mean() {
		return (sum / iterations);
	}
	// sample standard deviation of percolation threshold
	public double stddev() {
		double mean = mean();
		double sum = 0;
		for (int i = 0; i < percolations.length; i++) {
			sum += (percolations[i] - mean) * (percolations[i] - mean);  
		}
		return Math.sqrt((sum / (iterations - 1)));
	}
	
	// low  endpoint of 95% confidence interval
	public double confidenceLo() {
		return mean() - ((1.96 * stddev()) / Math.sqrt(iterations));
	}
	
	// high endpoint of 95% confidence interval
	public double confidenceHi() {
		return mean() + ((1.96 * stddev()) / Math.sqrt(iterations));
	}

	// test client (described below)
	public static void main(String[] args) {
		if (args.length < 2) {
			throw new IllegalArgumentException();
		}
		PercolationStats stats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		System.out.println("mean: " + stats.mean());
		System.out.println("std: " + stats.stddev());
		System.out.println("Confidence Interval: [" + stats.confidenceLo() + "," + stats.confidenceHi());
	}
}