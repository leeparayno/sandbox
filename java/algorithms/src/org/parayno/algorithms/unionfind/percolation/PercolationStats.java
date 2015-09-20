package org.parayno.algorithms.unionfind.percolation;

import java.util.Random;

import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    // private int sum = 0;
    private int iterations = 0;
    private double[] percolations;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T) {
        if (N <= 0) {
            throw new IllegalArgumentException("N must be a positive number. Given:" + N);
        }
        if (T <= 0) {
            throw new IllegalArgumentException("T must be a positive number. Given:" + T);
        }
        percolations = new double[T];
        for (int i = 0; i < T; i++) {
            double openSites = 0.0;
            Percolation perc = new Percolation(N);
            while (!perc.percolates()) {
                // Usually this can be a field rather than a method variable
                Random rand = new Random();

                // nextInt is normally exclusive of the top value,
                // so add 1 to make it inclusive
                int min = 1;
                int x = rand.nextInt((N - min) + 1) + min;
                int y = rand.nextInt((N - min) + 1) + min;
                // System.out.println("Opening: " + x + "," + y);
                if (!perc.isOpen(x, y)) {
                    perc.open(x, y);
                    openSites++;
                }
                // System.out.println("OPEN SITES: " + openSites + ", TOTAL SITES:" + (N * N));

            }

            // System.out.println("Xt:" + (openSites / (N * N)));
            percolations[i] = (openSites / (N * N));
            // sum += openSites;
        }
        iterations = T;
    }

    // sample mean of percolation threshold
    public double mean() {
        // return (sum / iterations);
        return StdStats.mean(percolations);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        /*
         * double mean = mean(); double sum = 0; for (int i = 0; i < percolations.length; i++) { sum += (percolations[i] - mean)
         * * (percolations[i] - mean); } return Math.sqrt((sum / (iterations - 1)));
         */
        return StdStats.stddev(percolations);
    }

    // low endpoint of 95% confidence interval
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
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        if (N <= 0) {
            throw new IllegalArgumentException("N must be a positive number. Given:" + N);
        }
        if (T <= 0) {
            throw new IllegalArgumentException("T must be a positive number. Given:" + T);
        }
        PercolationStats stats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        System.out.println("mean = " + stats.mean());
        System.out.println("stddev = " + stats.stddev());
        System.out.println("95% confidence interval = " + stats.confidenceLo() + "," + stats.confidenceHi());
    }
}