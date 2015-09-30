/**
 * Copyright 2015 Apple, Inc
 * Apple Internal Use Only
 **/


package org.parayno.algorithms.datastructures.queue;

import edu.princeton.cs.algs4.StdIn;

public class Subset {
    public static void main(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException("Must pass the number of elements to output.");
        }
        int quantity = Integer.parseInt(args[0]);
        Deque<String> deque = new Deque<>();
        
        while (!StdIn.isEmpty()) {
            String arg = StdIn.readString();

            deque.addFirst(arg);
        }
        
        for (int i = 0; i < quantity; i++) {
            System.out.println(deque.removeFirst());
        }
    }
    
}
