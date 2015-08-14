package org.parayno.algorithms.find;

import org.parayno.system.*;

public class QuickFind {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int N = Integer.parseInt(args[0]);
		int id[] = new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
		}
		for (In.init(); !In.empty();) {
			int p = In.getInt(), q = In.getInt();
			int t = id[p];
			if (t == id[q]) continue;
			for (int i = 0; i < N; i++) {
				if (id[i] == t) id[i] = id[q];				
			}
			Out.println(" " + p + " " + q);
		}
	}

}
