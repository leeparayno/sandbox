/**
 * Copyright 2015 Apple, Inc
 * Apple Internal Use Only
 **/


package org.parayno.algorithms.datastructures.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Node<Item> first = null;
    private Node<Item> last = null;
    private int size = 0;

    private class Node<T> {
        private T item;
        private Node<T> previous;
        private Node<T> next;

        public Node(T anItem) {
            item = anItem;
        }

        /**
         * @return
         */
        public T getItem() {
            return item;
        }

        /**
         * @return
         */
        public Node<T> getPrevious() {
            return previous;
        }

        /**
         * @param previous
         */
        public void setPrevious(Node<T> previous) {
            this.previous = previous;
        }

        /**
         * @return
         */
        public Node<T> getNext() {
            return next;
        }

        /**
         * @param next
         */
        public void setNext(Node<T> next) {
            this.next = next;
        }

    }
    
    /*
     * construct an empty randomized queue
     */
    public RandomizedQueue() {
        
    }
    
    /*
     * is the queue empty?
     */
    public boolean isEmpty() {
        return (size == 0);
    }
    
    /*
     * return the number of items on the queue
     */
    public int size() {
        return size;
    }
    
    /*
     * add the item
     */
    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException("Cannot add a null item to the queue. Item is null.");
        }
        size++;
        Node<Item> oldFirst = first;
        first = new Node<>(item);
        if (oldFirst != null) {
            first.setNext(oldFirst);
            oldFirst.setPrevious(first);
        } else {
            // Must be first item on the deque
            last = first;
        }        
    }
    
    /*
     * remove and return a random item
     */
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty. No more items to remove.");
        }
        
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int min = 1;
        int x = rand.nextInt((size - min) + 1) + min;
        
        
        Item aNode = first.getItem();
        Node<Item> nextFirst = first.getNext();
        if (nextFirst != null) {
            first = nextFirst;
            nextFirst.setPrevious(null);
        } else {
            // Must be last item on the deque
            first = null;
            last = null;
        }
        size--;
        return aNode;        
    }
    
    /*
     * return (but do not remove) a random item
     */
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty. No more items to remove.");
        }
        
        
        
        Item aNode = first.getItem();
        Node<Item> nextFirst = first.getNext();
        if (nextFirst != null) {
            first = nextFirst;
            nextFirst.setPrevious(null);
        } else {
            // Must be last item on the deque
            first = null;
            last = null;
        }
        size--;
        return aNode;           
    }
    
    /*
     * return an independent iterator over items in random order
     */
    public Iterator<Item> iterator() {
        return new QueueIterator<>(first);
    }
    
    private class QueueIterator<Item> implements Iterator<Item> {
        private Node<Item> previous;
        private Node<Item> next;
        private int removed = 0;

        public QueueIterator(Node<Item> node) {
            this.next = node;
        }

        @Override
        public boolean hasNext() {
            return (next != null);
        }

        @Override
        public Item next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            previous = next;
            next = next.next;
            return previous.item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }    
    
    /*
     * unit testing
     */
    public static void main(String[] args) {
        
    }
 }
