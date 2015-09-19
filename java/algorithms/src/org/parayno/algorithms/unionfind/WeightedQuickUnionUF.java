package org.parayno.algorithms.unionfind;

public class WeightedQuickUnionUF {
	private int[] parent;   // parent[i] = parent of i
    private int[] size;     // size[i] = number of objects in subtree rooted at i
    private int count;      // number of components	
	
	
	public WeightedQuickUnionUF(int N) {
        count = N;
        parent = new int[N];
        size = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
            size[i] = 1;
        }		
	}
	
    public int count() {
        return count;
    }	
	
    public int find(int p) {
        validate(p);
        while (p != parent[p])			// chase parent pointers until reach root
            p = parent[p];				// (depth of i array accesses)
        return p;
    }
    
    // validate that p is a valid index
    private void validate(int p) {
        int N = parent.length;
        if (p < 0 || p >= N) {
            throw new IndexOutOfBoundsException("index " + p + " is not between 0 and " + N);
        }
    }    
    
	public boolean connected(int p, int q) {
		return find(p) == find(q);		// check if p and q have same root
	}									// (depth of p and q array accesses)
	
	
	public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;

        // make smaller root point to larger one
        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }
        else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
        count--;		
	}
}
