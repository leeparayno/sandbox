package org.parayno.algorithms.datastructures;

import java.util.HashMap;


/* Use of a HashMap */
public class Students {

	private class Student {
		int id = 0;
		
		public int getId() {
			return id;
		}
		
		public void setId(int aId) {
			id = aId;
		}
	}	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public HashMap<Integer, Student> buildMap(Student[] students) {
		HashMap<Integer, Student> map = new HashMap<Integer, Student>();
		for (Student s : students) map.put(s.getId(),s);
		return map;
	}
	
}
