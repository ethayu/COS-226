package Queues;

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int size;

    // Node class.
    private class Node {
        Item item;
        Node next;
        Node prev;

        // Constructor.
        public Node(Item item, Node next, Node prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        size = 0;
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
            throw new java.lang.IllegalArgumentException("item to add is null");
        }
        first = new Node(item, first, null);
        if (isEmpty()) {
            last = first;
        } else {
            first.next.prev = first;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new java.lang.IllegalArgumentException("item to add is null");
        }
        last = new Node(item, null, last);
        if (isEmpty()) {
            first = last;
        } else {
            last.prev.next = last;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("deque is empty");
        }
        Item ret = first.item;
        first = first.next;
        size--;
        if (isEmpty()) {
            last = null;
        } else {
            first.prev = null;
        }
        return ret;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("deque is empty");
        }
        Item ret = last.item;
        last = last.prev;
        size--;
        if (isEmpty()) {
            first = null;
        } else {
            last.next = null;
        }
        return ret;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node curr = first;
        public boolean hasNext() {
            return curr != null;
        }
        public void remove() {
            throw new java.lang.UnsupportedOperationException("unsupported operation");
        }
        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException("you've reached the end of the deque");
            }
            Item ret = curr.item;
            curr = curr.next;
            return ret;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        System.out.println("Initializing new Queues.Deque");
        Deque<Integer> deque = new Deque<>();
        System.out.println("Is deque empty?: " + deque.isEmpty());
        System.out.println();
        System.out.println("Adding 5 elements as follows:");
        System.out.println("add 1 to beginning; add 2 to beginning; add 3 to end; add 4 to beginning; add 5 to end.");
        deque.addFirst(1); deque.addFirst(2); deque.addLast(3); deque.addFirst(4); deque.addLast(5);
        System.out.println();
        System.out.println("expected size: 5; function result: " + deque.size());
        System.out.println("creating iterator to iterate deque");
        Iterator iterator = deque.iterator();
        System.out.println("expected result, from first to last node: 4 2 1 3 5");
        System.out.print("iterating through 5 elements of deque through iterator: ");
        System.out.println(iterator.next() + " " + iterator.next() + " "  + iterator.next() + " "  + iterator.next() + " "  + iterator.next());
        System.out.println();
        System.out.println("removing first element; expected result: 2 1 3 5");
        deque.removeFirst();
        iterator = deque.iterator();
        System.out.print("actual result: ");
        System.out.println(iterator.next() + " " + iterator.next() + " "  + iterator.next() + " "  + iterator.next());
        System.out.println();
        System.out.println("removing last element; expected result: 2 1 3");
        deque.removeLast();
        iterator = deque.iterator();
        System.out.print("actual result: ");
        System.out.println(iterator.next() + " " + iterator.next() + " "  + iterator.next());
        System.out.println();
        System.out.println("testing hasNext() of iterator (should be false): " + iterator.hasNext());
        System.out.println("testing remove() of iterator (should throw unsupported operation error)");
        iterator.remove();
    }

}