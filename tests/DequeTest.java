import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DequeTest {

    @Test
    void isEmpty() {
    }

    @Test
    void size() {
        Deque<String> deque = new Deque<>();

        deque.addFirst("first");
        assertEquals(1, deque.size());
    }

    @Test
    void testAddFirst() {
        Deque<String> deque = new Deque<>();

        deque.addFirst("first");
        assertEquals(1, deque.size());
        assertFalse(deque.isEmpty());
    }

    @Test
    void addLast() {
    }

    @Test
    void removeFirst() {
    }

    @Test
    void removeLast() {
    }
}