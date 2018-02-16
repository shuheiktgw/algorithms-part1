import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int size;

    private class Node {
        private Node next;
        private Node prev;
        private Item item;

        public Node(Node nextNode, Node prevNode , Item newItem) {
            this.next = nextNode;
            this.prev = prevNode;
            this.item = newItem;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public Item getItem() {
            return item;
        }

        public void setItem(Item item) {
            this.item = item;
        }
    }

    public Deque() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return getFirst() == null;
    }

    // return the number of items on the deque
    public int size() {
        return this.size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        Node node = new Node(first, null, item);
        setSize(size + 1);

        // Means first addition
        if (getFirst() == null && getLast() == null) {
            setLast(node);
        } else {
            getFirst().setPrev(node);
        }

        setFirst(node);
    }

    // add the item to the end
    public void addLast(Item item) {

    }

    // remove and return the item from the front
    public Item removeFirst() {
        return null;
    }

    // remove and return the item from the end
    public Item removeLast() {
        return null;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            /* not supported */
        }

        public Item next() {
            Item item = current.getItem();
            current = current.getNext();
            return item;
        }
    }

    private Node getFirst() {
        return this.first;
    }

    private void setFirst(Node node) {
        this.first = node;
    }

    private Node getLast() {
        return this.last;
    }

    private void setLast(Node node) {
        this.last = node;
    }

    private void setSize(int size) {
        this.size = size;
    }
}