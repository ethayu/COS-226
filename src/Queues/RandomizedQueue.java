package Queues;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;
    private int size;

    // construct an empty randomized queue
    public RandomizedQueue() {
        items = (Item[]) new Object[1];
        size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
         if (item == null) {
            throw new java.lang.IllegalArgumentException("item to add is null");
         }
         if (size == items.length) { // increase size of array if completely filled
            Item[] temp = (Item[]) new Object[size * 2];
             for (int i = 0; i < items.length; i++) temp[i] = items[i];
             items = temp;
         }
         items[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("already-empty queue");
        }
        int randomIndex = (int) (Math.random() * size);
        Item ret = items[randomIndex];
        items[randomIndex] = items[--size];
        return ret;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("empty queue");
        }
        return items[(int) (Math.random() * size)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    public class RandomizedQueueIterator implements Iterator<Item> {
        private int[] order;
        public int curr;

        public RandomizedQueueIterator() {
            order = new int[size];
            for (int i = 0; i < size; i++) order[i] = i;
            for (int i = size - 1; i >= 0; i--) { //Fisher-Yates Shuffle
                int randomIndex = (int) (Math.random() * i);
                int temp = order[i];
                order[i] = order[randomIndex];
                order[randomIndex] = temp;
            }
            curr = 0;
        }

        public boolean hasNext() {
            return curr < size;
        }

        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException("iteration complete");
            }
            return items[order[curr++]];
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException("unsupported operation");
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        System.out.println("Initializing new Queues.RandomizedQueue");
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();
        System.out.println("Is randomizedQueue empty?: " + randomizedQueue.isEmpty());
        System.out.println();
        System.out.println("Adding numbers 1 through 5");
        randomizedQueue.enqueue(1); randomizedQueue.enqueue(2); randomizedQueue.enqueue(3); randomizedQueue.enqueue(4); randomizedQueue.enqueue(5);
        System.out.println();
        System.out.println("expected size: 5; function result: " + randomizedQueue.size());
        System.out.println("creating iterator to iterate randomizedQueue");
        Iterator iterator = randomizedQueue.iterator();
        System.out.print("random iteration of the 5 elements of randomizedQueue: ");
        System.out.println(iterator.next() + " " + iterator.next() + " "  + iterator.next() + " "  + iterator.next() + " "  + iterator.next());
        System.out.println();
        System.out.println("removing element " + randomizedQueue.dequeue() + ":");
        iterator = randomizedQueue.iterator();
        System.out.print("random iteration of remaining 4 elements of randomizedQueue: ");
        System.out.println(iterator.next() + " " + iterator.next() + " "  + iterator.next() + " "  + iterator.next());
        System.out.println();
        System.out.println("random sample of randomizedQueue: " + randomizedQueue.sample());
        System.out.println("testing hasNext() of iterator (should be false): " + iterator.hasNext());
        System.out.println("testing remove() of iterator (should throw unsupported error)");
        iterator.remove();

    }

}