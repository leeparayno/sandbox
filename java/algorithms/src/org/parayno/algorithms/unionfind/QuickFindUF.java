package org.parayno.algorithms.unionfind;


public class QuickFindUF {
	private int[] id;
	
	public QuickFindUF(int N) {
		id = new int[N];						// set id of each object to itself
		for (int i = 0; i < N; i++)				// (N array accesses)
			id[i] = i;
	}
	

	public boolean connected(int p, int q) {		// check whether p and q
		return id[p] == id[q];					// are in the same component
	}									// (2 array accesses)

	public void union(int p, int q) {
		int pid = id[p];
		int qid = id[q];
		for (int i = 0; i < id.length; i++) {		// Change all entries with id[p] to id[q]
			if (id[i] == pid) id[i] = qid;			// (at most 2N + 2 array accesses)
		}
	}

}