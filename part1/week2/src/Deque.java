import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int size = 0;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    private Node head;
    private Node tail;


    // construct an empty deque
    public Deque() {
        head = null;
        tail = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item in addFirst is null!");
        }
        Node old_head = head;
        Node new_node = new Node();
        new_node.item = item;

        size++;
        if (old_head == null) {
            // First item
            head = new_node;
            tail = new_node;
            return;
        }
        head = new_node;
        head.next = old_head;
        old_head.prev = head;
        if (tail == null) {
            tail = head;
        }
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item in addLast is null!");
        }
        Node new_node = new Node();
        new_node.item = item;
        Node old_tail = tail;

        size++;
        if (old_tail == null) {
            // First item
            head = new_node;
            tail = new_node;
            return;
        }
        tail = new_node;
        tail.prev = old_tail;
        old_tail.next = tail;
        if (head == null) {
            head = tail;
        }

    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("No item in removeFirst");
        }
        Item item = head.item;
        head = head.next;
        size--;
        if (head != null && head.next != null)
            head.prev = null;

        if (tail == null)
            head = null;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("No item in removeLast");
        }
        Item item = tail.item;
        tail = tail.prev;
        size--;
        if (tail != null && tail.prev != null)
            tail.next = null;

        if (head == null)
            tail = null;

        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = head;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No item in next");
            }

            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {

        Deque<Integer> deque = new Deque<>();
        deque.addFirst(3);
        deque.addLast(4);
        deque.addFirst(2);
        deque.addFirst(1);
        deque.addLast(5);
        deque.addFirst(0);

        for (Integer item : deque)
            StdOut.println(item);
        StdOut.printf("Size before remove = %d%n", deque.size());
        Integer x = deque.removeFirst();
        StdOut.printf("x %d%n", x);
        x = deque.removeFirst();
        StdOut.printf("x %d%n", x);
        x = deque.removeLast();
        StdOut.printf("x %d%n", x);
        StdOut.printf("Size after remove = %d%n", deque.size());
        for (Integer item : deque)
            StdOut.println(item);
        x = deque.removeLast();
        StdOut.printf("x %d%n", x);
        x = deque.removeLast();
        StdOut.printf("x %d%n", x);
        x = deque.removeLast();
        StdOut.printf("x %d%n", x);
    }

}
