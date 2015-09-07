package org.parayno.algorithms.unionfind;

public class WeightedQuickUnionUF {
	private int[] id;
	
	public WeightedQuickUnionUF(int N) {
		id = new int[N];
		for (int i = 0; i < N; i++)		// set id of each object to itself 
			id[i] = i;					// N array accesses
	}
	
	private int root(int i) {
		while (i != id[i])				// chase parent pointers until reach root
			i = id[i];					// (depth of i array accesses)
		return i;
	}
	
	public boolean connected(int p, int q) {
		return root(p) == root(q);		// check if p and q have same root
	}									// (depth of p and q array accesses)
	
	
	public void union(int p, int q) {
		int i = root(p);				// change root of p to point to root of q
		int j = root(q);				// (depth of p and q array accesses)
		id[i] = j;
	}
}
