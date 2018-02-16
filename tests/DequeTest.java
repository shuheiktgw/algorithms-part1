import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

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
    void testAddLast() {
        deque.addLast("first");
        assertEquals(1, deque.size());
        assertFalse(deque.isEmpty());

        deque.addLast("second");
        assertEquals(2, deque.size());
        assertFalse(deque.isEmpty());

        deque.addLast("third");
        assertEquals(3, deque.size());
        assertFalse(deque.isEmpty());
    }

    @Test
    void testAddLastFailsWithNullItem() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> deque.addLast(null));
        assertEquals("Item cannot be null", exception.getMessage());
    }

    @Test
    void testRemoveFirst() {
        deque.addFirst("first");
        assertEquals(1, deque.size());
        assertFalse(deque.isEmpty());

        deque.addFirst("second");
        assertEquals(2, deque.size());
        assertFalse(deque.isEmpty());

        deque.addFirst("third");
        assertEquals(3, deque.size());
        assertFalse(deque.isEmpty());

        assertEquals("third", deque.removeFirst());
        assertEquals(2, deque.size());
        assertFalse(deque.isEmpty());

        assertEquals("second", deque.removeFirst());
        assertEquals(1, deque.size());
        assertFalse(deque.isEmpty());

        assertEquals("first", deque.removeFirst());
        assertEquals(0, deque.size());
        assertTrue(deque.isEmpty());

        Exception exception = assertThrows(NoSuchElementException.class, () -> deque.removeFirst());
        assertEquals("Deque size is 0", exception.getMessage());
    }

    @Test
    void testRemoveLast() {
        deque.addFirst("first");
        assertEquals(1, deque.size());
        assertFalse(deque.isEmpty());

        deque.addFirst("second");
        assertEquals(2, deque.size());
        assertFalse(deque.isEmpty());

        deque.addFirst("third");
        assertEquals(3, deque.size());
        assertFalse(deque.isEmpty());

        assertEquals("first", deque.removeLast());
        assertEquals(2, deque.size());
        assertFalse(deque.isEmpty());

        assertEquals("second", deque.removeLast());
        assertEquals(1, deque.size());
        assertFalse(deque.isEmpty());

        assertEquals("third", deque.removeLast());
        assertEquals(0, deque.size());
        assertTrue(deque.isEmpty());

        Exception exception = assertThrows(NoSuchElementException.class, () -> deque.removeLast());
        assertEquals("Deque size is 0", exception.getMessage());
    }

    @Test
    void testAdditionAndRemoval() {
        deque.addFirst("first");
        assertEquals(1, deque.size());
        assertFalse(deque.isEmpty());

        deque.addLast("second");
        assertEquals(2, deque.size());
        assertFalse(deque.isEmpty());

        deque.addFirst("third");
        assertEquals(3, deque.size());
        assertFalse(deque.isEmpty());

        deque.addLast("fourth");
        assertEquals(4, deque.size());
        assertFalse(deque.isEmpty());

        assertEquals("third", deque.removeFirst());
        assertEquals(3, deque.size());
        assertFalse(deque.isEmpty());

        deque.addLast("third");
        assertEquals(4, deque.size());
        assertFalse(deque.isEmpty());

        assertEquals("first", deque.removeFirst());
        assertEquals(3, deque.size());
        assertFalse(deque.isEmpty());

        assertEquals("third", deque.removeLast());
        assertEquals(2, deque.size());
        assertFalse(deque.isEmpty());

        assertEquals("fourth", deque.removeLast());
        assertEquals(1, deque.size());
        assertFalse(deque.isEmpty());

        assertEquals("second", deque.removeFirst());
        assertEquals(0, deque.size());
        assertTrue(deque.isEmpty());

        Exception lastException = assertThrows(NoSuchElementException.class, () -> deque.removeLast());
        assertEquals("Deque size is 0", lastException.getMessage());

        Exception firstException = assertThrows(NoSuchElementException.class, () -> deque.removeFirst());
        assertEquals("Deque size is 0", firstException.getMessage());
    }
}