import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Node first = null;
    private int size = 0;

    private class Node {
        private Node next;
        private Node prev;
        private Item item;

        public Node(Node nextNode, Node prevNode, Item item) {
            this.next = nextNode;
            this.prev = prevNode;
            this.item = item;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Item getItem() {
            return item;
        }

        public Node getPrev() {
            return prev;
        }

        public Node getNext() {
            return next;
        }
    }

    // construct an empty randomized queue
    public RandomizedQueue() { }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return this.size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return this.size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }

        Node node = new Node(first, null, item);

        // Means not the first addition
        if (this.size > 0) {
            this.first.setPrev(node);
        }

        this.size++;
        this.first = node;
    }

    // remove and return a random item
    public Item dequeue() {
        checkSize();

        Node middle = pickup();

        if (this.size == 1) {
            assert (middle == first);
            this.first = null;
        } else {
            Node prev = middle.getPrev();
            Node next = middle.getNext();

            if (prev != null) {
                prev.setNext(next);
            } else {
                // Means picked up the first node
                assert (middle == first);
                this.first = next;
            }

            if (next != null) {
                next.setPrev(prev);
            }
        }

        this.size--;
        return middle.getItem();
    }

    // return a random item (but do not remove it)
    public Item sample() {
        checkSize();
        return pickup().getItem();
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        public boolean hasNext() {
            return !isEmpty();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Deque size is 0");
            }

            return dequeue();
        }
    }

    private Node pickup() {
        int index = StdRandom.uniform(this.size);

        Node node = this.first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }

        return node;
    }

    private void checkSize() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque size is 0");
        }
    }
}
