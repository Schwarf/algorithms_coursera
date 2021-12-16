import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size = 0;
    private int capacity = 5;
    private Item[] array;

    // construct an empty randomized queue
    public RandomizedQueue() {
        array = (Item[]) new Object[capacity];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    private void double_capacity() {
        capacity *= 2;
        Item[] new_array = (Item[]) new Object[capacity];
        System.arraycopy(array, 0, new_array, 0, size);
        array = new_array;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Randomized queue null argument: dequeue");
        }
        array[size] = item;
        size++;
        if (size == capacity)
            double_capacity();
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Randomized queue is empty: dequeue");
        }
        int current = StdRandom.uniform(size);
        Item item = array[current];
        if (current != size - 1)
            array[current] = array[size - 1];
        size--;
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("Randomized queue is empty: sample");
        }
        int current = StdRandom.uniform(size);
        return array[current];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int current = 0;

        public boolean hasNext() {
            return current < size;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No item in next");
            }

            Item item = array[current];
            current++;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }


    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> randomize_queue = new RandomizedQueue<>();
        randomize_queue.enqueue(5);
        randomize_queue.enqueue(4);
        randomize_queue.enqueue(3);
        randomize_queue.enqueue(2);
        randomize_queue.enqueue(1);
        StdOut.printf("size= %d%n", randomize_queue.size());
        for (Integer i : randomize_queue) {
            StdOut.printf("iterate %d%n", i);
        }
        StdOut.printf("random 1 = %d%n", randomize_queue.dequeue());
        StdOut.printf("random 2 = %d%n", randomize_queue.dequeue());
        StdOut.printf("size= %d%n", randomize_queue.size());
        randomize_queue.enqueue(4);
        randomize_queue.enqueue(4);
        randomize_queue.enqueue(4);
        StdOut.printf("size= %d%n", randomize_queue.size());
        for (Integer i : randomize_queue) {
            StdOut.printf("iterate %d%n", i);
        }
        StdOut.printf("random sample 1 = %d%n", randomize_queue.sample());
        StdOut.printf("random sample 2 = %d%n", randomize_queue.sample());
        StdOut.printf("random sample 3 = %d%n", randomize_queue.sample());

    }

}
