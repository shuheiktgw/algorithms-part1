import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class RandomizedQueueTest {

    private RandomizedQueue<String> queue;

    @BeforeEach
    void setup(){
        queue = new RandomizedQueue<>();
    }

    @Test
    void testIsEmpty() {
        assertTrue(queue.isEmpty());
    }

    @Test
    void testSize() {
        assertEquals(0, queue.size());
    }

    @Test
    void testEnqueue() {
        queue.enqueue("first");
        assertFalse(queue.isEmpty());
        assertEquals(1, queue.size());

        queue.enqueue("second");
        assertFalse(queue.isEmpty());
        assertEquals(2, queue.size());

        queue.enqueue("third");
        assertFalse(queue.isEmpty());
        assertEquals(3, queue.size());

        queue.enqueue("four");
        assertFalse(queue.isEmpty());
        assertEquals(4, queue.size());

        queue.enqueue("five");
        assertFalse(queue.isEmpty());
        assertEquals(5, queue.size());
    }

    @Test
    void testDequeue() {
        List<String> items = new ArrayList<>(Arrays.asList("first", "second", "third", "fourth", "fifth"));

        for (String item : items) {
            queue.enqueue(item);
        }

        assertTrue(items.contains(queue.dequeue()));
        assertFalse(queue.isEmpty());
        assertEquals(4, queue.size());


        assertTrue(items.contains(queue.dequeue()));
        assertFalse(queue.isEmpty());
        assertEquals(3, queue.size());

        assertTrue(items.contains(queue.dequeue()));
        assertFalse(queue.isEmpty());
        assertEquals(2, queue.size());

        assertTrue(items.contains(queue.dequeue()));
        assertFalse(queue.isEmpty());
        assertEquals(1, queue.size());

        assertTrue(items.contains(queue.dequeue()));
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());

        Exception exception = assertThrows(NoSuchElementException.class, queue::dequeue);
        assertEquals("Deque size is 0", exception.getMessage());
    }

    @Test
    void testLargeDequeue() {
        List<String> items = new ArrayList<>();
        for (int i = 0; i < 1000; i ++) {
            items.add(String.valueOf(i));
        }

        for (String item : items) {
            queue.enqueue(item);
        }

        for (int i = 0; i < 999; i++) {
            assertTrue(items.contains(queue.dequeue()));
            assertFalse(queue.isEmpty());
            assertEquals(999 - i, queue.size());
        }

        assertTrue(items.contains(queue.dequeue()));
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());

        Exception exception = assertThrows(NoSuchElementException.class, queue::dequeue);
        assertEquals("Deque size is 0", exception.getMessage());
    }

    @Test
    void testSample() {
        List<String> items = new ArrayList<>(Arrays.asList("first", "second", "third", "fourth", "fifth"));

        for (String item : items) {
            queue.enqueue(item);
        }

        for (int i = 0; i < 100; i++) {
            assertTrue(items.contains(queue.sample()));
            assertFalse(queue.isEmpty());
            assertEquals(5, queue.size());
        }
    }

    @Test
    void testIterator() {
        List<String> items = new ArrayList<>(Arrays.asList("first", "second", "third", "fourth", "fifth"));

        for (String item : items) {
            queue.enqueue(item);
        }

        Iterator<String> iterator = queue.iterator();

        assertTrue(iterator.hasNext());
        assertTrue(items.contains(iterator.next()));

        assertTrue(iterator.hasNext());
        assertTrue(items.contains(iterator.next()));

        assertTrue(iterator.hasNext());
        assertTrue(items.contains(iterator.next()));

        assertTrue(iterator.hasNext());
        assertTrue(items.contains(iterator.next()));

        assertTrue(iterator.hasNext());
        assertTrue(items.contains(iterator.next()));

        assertFalse(iterator.hasNext());
        Exception exception = assertThrows(NoSuchElementException.class, iterator::next);
        assertEquals("Deque size is 0", exception.getMessage());

        assertThrows(UnsupportedOperationException.class, iterator::remove);
    }
}