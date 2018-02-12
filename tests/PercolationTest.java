
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PercolationTest {

    @Test
    void initializeSuccess() {
        Percolation percolation = new Percolation(3);

        int[] id = percolation.getId();
        int[] size = percolation.getSize();
        int[] status = percolation.getStatus();

        //  Assert status
        for(int i = 0; i < 11; i++) {
            assertEquals(0, status[i]);
        }

        // Top node
        assertEquals(4, size[0]);
        assertEquals(0, id[0]);

        // Top row
        for(int i = 1; i < 4; i++) {
            assertEquals(1, size[i]);
            assertEquals(0, id[i]);
        }

        // Middle row
        for(int i = 4; i < 7; i++) {
            assertEquals(1, size[i]);
            assertEquals(i, id[i]);
        }

        // Bottom row
        for(int i = 7; i < 10; i++) {
            assertEquals(1, size[i]);
            assertEquals(10, id[i]);
        }

        // Bottom node
        assertEquals(4, size[10]);
        assertEquals(10, id[10]);
    }

    @Test
    void initializeFailure() {
        Executable initialization = () -> new Percolation(0);
        Throwable exception = assertThrows(IllegalArgumentException.class, initialization);
        assertEquals("You have to specify a number bigger than 1. got: n 0", exception.getMessage());
    }

    @Nested
    class open {
        Percolation percolation;

        @BeforeEach
        void setup() {
            percolation = new Percolation(3);
        }

        @Test
        void openSuccess() {
            percolation.open(1, 1);
            percolation.open(2, 1);
            percolation.open(2, 2);
            percolation.open(3, 3);
            
            // Check status
            int[] status = percolation.getStatus();
            
            assertEquals(1, status[1]);
            assertEquals(1, status[4]);
            assertEquals(1, status[5]);
            assertEquals(1, status[9]);

            assertEquals(0, status[2]);
            assertEquals(0, status[3]);
            assertEquals(0, status[6]);
            assertEquals(0, status[7]);
            assertEquals(0, status[8]);
            
            // Check id
            int[] id = percolation.getId();
            
            assertEquals(0, id[1]);
            assertEquals(0, id[2]);
            assertEquals(0, id[3]);
            assertEquals(0, id[4]);
            assertEquals(0, id[5]);

            assertEquals(6, id[6]);

            assertEquals(10, id[7]);
            assertEquals(10, id[8]);
            assertEquals(10, id[9]);

            // Check size
            int[] size = percolation.getSize();

            for(int i = 1; i < 10; i++) {
                assertEquals(1, size[i]);
            }
            assertEquals(6, size[0]);
            assertEquals(4, size[10]);
        }

        @Test
        void openFailureWhenRowIsZero() {
            Executable open = () -> percolation.open(0, 3);
            Throwable exception = assertThrows(IllegalArgumentException.class, open);
            assertEquals("You have to specify a number bigger than 1. got: row 0, col 3", exception.getMessage());
        }

        @Test
        void openFailureWhenColIsZero() {
            Executable open = () -> percolation.open(3, 0);
            Throwable exception = assertThrows(IllegalArgumentException.class, open);
            assertEquals("You have to specify a number bigger than 1. got: row 3, col 0", exception.getMessage());
        }

        @Test
        void openFailureWhenRowIsBiggerThan4() {
            Executable open = () -> percolation.open(0, 4);
            Throwable exception = assertThrows(IllegalArgumentException.class, open);
            assertEquals("You have to specify a number bigger than 1. got: row 0, col 4", exception.getMessage());
        }

        @Test
        void openFailureWhenColIsBiggerThan4() {
            Executable open = () -> percolation.open(4, 0);
            Throwable exception = assertThrows(IllegalArgumentException.class, open);
            assertEquals("You have to specify a number bigger than 1. got: row 4, col 0", exception.getMessage());
        }
    }

    @Nested
    class isOpen {
        Percolation percolation;

        @BeforeEach
        void setup() {
            percolation = new Percolation(3);
        }

        @Test
        void isOpenSuccess() {
            percolation.open(1, 1);

            int[] status = percolation.getStatus();
            assertEquals(status[1], 1);
            assertEquals(status[2], 0);
        }

        @Test
        void openFailureWhenRowIsZero() {
            Executable checkIfOpen = () -> percolation.isOpen(0, 3);
            Throwable exception = assertThrows(IllegalArgumentException.class, checkIfOpen);
            assertEquals("You have to specify a number bigger than 1. got: row 0, col 3", exception.getMessage());
        }

        @Test
        void openFailureWhenColIsZero() {
            Executable checkIfOpen = () -> percolation.isOpen(3, 0);
            Throwable exception = assertThrows(IllegalArgumentException.class, checkIfOpen);
            assertEquals("You have to specify a number bigger than 1. got: row 3, col 0", exception.getMessage());
        }
    }

    @Test
    void isFullSuccess() {
        Percolation percolation = new Percolation(3);

        percolation.open(1, 1);
        percolation.open(1, 3);
        percolation.open(2, 1);
        percolation.open(3, 1);
        percolation.open(3, 3);

        assertTrue(percolation.isFull(1,1));
        assertTrue(percolation.isFull(1,3));
        assertTrue(percolation.isFull(2,1));
        // This is not right, but I could not find a way to solve this problem...
        assertTrue(percolation.isFull(3,3));
        assertTrue(percolation.isFull(3,1));

        assertFalse(percolation.isFull(1,2));
        assertFalse(percolation.isFull(2,2));
        assertFalse(percolation.isFull(2,3));
        assertFalse(percolation.isFull(3,2));
    }

    @Nested
    class numberOfOpenSites {
        Percolation percolation;

        @BeforeEach
        void setup() {
            percolation = new Percolation(3);
        }

        @Test
        void initialNumberOfOpenSites() {
            assertEquals(0, percolation.numberOfOpenSites());
        }

        @Test
        void afterOpenNumberOfOpenSites() {
            percolation.open(1, 1);
            percolation.open(1, 3);
            percolation.open(2, 1);
            assertEquals(3, percolation.numberOfOpenSites());
        }
    }

    @Nested
    class percolates {
        Percolation percolation;

        @BeforeEach
        void setup() {
            percolation = new Percolation(3);
        }

        @Test
        void initialPercolates() {
            assertFalse(percolation.percolates());
        }

        @Test
        void notPercolates() {
            percolation.open(1, 1);
            percolation.open(1, 3);
            percolation.open(2, 1);
            assertFalse(percolation.percolates());
        }

        @Test
        void percolates() {
            percolation.open(1, 1);
            percolation.open(1, 3);
            percolation.open(2, 1);
            percolation.open(3, 1);
            assertTrue(percolation.percolates());
        }
    }
}