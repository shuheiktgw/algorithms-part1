import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DequeTest {
    private Deque<String> deque;

    @BeforeEach
    void setup(){
        deque = new Deque<>();
    }

    @Test
    void isEmpty() {
    }

    @Test
    void size() {
        deque.addFirst("first");
        assertEquals(1, deque.size());
    }

    @Test
    void testAddFirst() {
        deque.addFirst("first");
        assertEquals(1, deque.size());
        assertFalse(deque.isEmpty());

        deque.addFirst("second");
        assertEquals(2, deque.size());
        assertFalse(deque.isEmpty());

        deque.addFirst("third");
        assertEquals(3, deque.size());
        assertFalse(deque.isEmpty());
    }

    @Test
    void testAddFirstFailsWithNullItem() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> deque.addFirst(null));
        assertEquals("Item cannot be null", exception.getMessage());
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