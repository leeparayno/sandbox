/**
 * 
 */
package org.parayno.algorithms.datastructures.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author lparayno
 *
 */
public class Deque<Item> implements Iterable<Item> {
    private Node<Item> first;
    private Node<Item> last;
    private int dequeSize;

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

    public Deque() { // construct an empty deque
        dequeSize = 0;
        first = null;
        last = null;
    }

    public boolean isEmpty() { // is the deque empty?
        if (first == null && last == null) {
            return true;
        }
        return false;
    }

    public int size() { // return the number of items on the deque
        return dequeSize;
    }

    public void addFirst(Item item) { // add the item to the front
        if (item == null) {
            throw new NullPointerException("Cannot add a null item to the deque. Item is null.");
        }
        dequeSize++;
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

    public void addLast(Item item) { // add the item to the end
        if (item == null) {
            throw new NullPointerException("Cannot add a null item to the deque. Item is null.");
        }
        dequeSize++;
        Node<Item> oldLast = last;
        last = new Node<>(item);
        if (oldLast != null) {
            oldLast.setNext(last);
            last.setPrevious(oldLast);
        } else {
            // Must be first item on the deque
            first = last;
        }
    }

    public Item removeFirst() { // remove and return the item from the front
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
        dequeSize--;
        return aNode;
    }

    public Item removeLast() { // remove and return the item from the end
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty. No more items to remove.");
        }
        Node<Item> oldLast = last;
        Node<Item> newLast = last.getPrevious();
        if (newLast != null) {
            newLast.setNext(null);
            last = newLast;
        } else {
            // Must be the last item on the deque
            first = null;
            last = null;
        }
        dequeSize--;
        return oldLast.getItem();
    }

    /*
     * private static void printDeque(Deque deque) { Node<Item> current = deque.first; Out.println("Deque size: " +
     * deque.size()); while (current != null) { Out.println("->"); Out.println(current.item.toString()); Node<Item> next =
     * current.getNext(); current = next; } }
     */
    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Iterable#iterator()
     * 
     * return an iterator over items in order from front to end
     */
    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator<>(first);
    }

    // Need to throw java.lang.UnsupportedOperationException
    // when iterator's remove method is called
    // throw new UnsupportedOperationException

    // next() if isEmpty()
    // throw new java.util.NoSuchElementException

    private class DequeIterator<Item> implements Iterator<Item> {
        private Node<Item> previous;
        private Node<Item> next;

        public DequeIterator(Node<Item> node) {
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

    /**
     * @param args
     */
    public static void main(String[] args) {
        // Node<Item> current;
        Deque<String> cards = new Deque<>();
        System.out.println("Deque size: " + cards.size());
        cards.addFirst("Magic Johnson");
        System.out.println("Deque size after adding 1: " + cards.size());

        cards.addFirst("Byron Scott");
        System.out.println("Deque size after adding 2: " + cards.size());
        // printDeque(cards);
        cards.addLast("Michael Jordan");
        System.out.println("Deque size after adding 3: " + cards.size());

        Iterator<String> iterator = cards.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            System.out.println(i + ":" + iterator.next());
            i++;
        }

        System.out.println("Last: " + cards.removeLast());

        System.out.println("Deque size after removing last: " + cards.size());

        System.out.println("First: " + cards.removeFirst());

        System.out.println("Deque size after removing first: " + cards.size());

        cards.addFirst("Stephen Curry");

        System.out.println("Deque size after adding 1: " + cards.size());

        System.out.println("First: " + cards.removeFirst());

        System.out.println("Deque size after removing first: " + cards.size());

        cards.addLast("Michael Cooper");

        iterator = cards.iterator();
        i = 0;
        while (iterator.hasNext()) {
            System.out.println(i + ":" + iterator.next());
            i++;
        }

        System.out.println("Deque size after adding 1: " + cards.size());

        System.out.println("First: " + cards.removeFirst());

        System.out.println("Deque size after removing first: " + cards.size());

        try {
            System.out.println("First: " + cards.removeFirst());
        } catch (NoSuchElementException nse) {
            System.out.println("Tried to removeFirst");
            nse.printStackTrace();
        }
        try {
            System.out.println("First: " + cards.removeLast());
        } catch (NoSuchElementException nse) {
            System.out.println("Tried to removeLast");
            nse.printStackTrace();
        }
    }

}
