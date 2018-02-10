
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
        int[] status = percolation.getStatus();

        for(int i = 0; i < 11; i++) {
            assertEquals(i, id[i]);
            assertEquals(0, status[i]);
        }
    }

    @Test
    void initializeFailure() {
        Executable initialization = () -> new Percolation(0);
        assertThrows(IllegalArgumentException.class, initialization, "You have to specify a number bigger than 1.");
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
            percolation.open(2, 2);
            percolation.open(3, 3);

            int[] status = percolation.getStatus();
            
            assertEquals(status[0], 1);
            assertEquals(status[4], 1);
            assertEquals(status[8], 1);

            assertEquals(status[1], 0);
            assertEquals(status[2], 0);
            assertEquals(status[3], 0);
            assertEquals(status[5], 0);
            assertEquals(status[6], 0);
            assertEquals(status[7], 0);
        }

        @Test
        void openFailureWhenRowIsZero() {
            Executable initialization = () -> percolation.open(0, 3);
            assertThrows(IllegalArgumentException.class, initialization, "You have to specify a number bigger than 1.");
        }

        @Test
        void openFailureWhenColIsZero() {
            Executable initialization = () -> percolation.open(3, 0);
            assertThrows(IllegalArgumentException.class, initialization, "You have to specify a number bigger than 1.");
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
            assertEquals(status[0], 1);
            assertEquals(status[1], 0);
        }

        @Test
        void openFailureWhenRowIsZero() {
            Executable initialization = () -> percolation.isOpen(0, 3);
            assertThrows(IllegalArgumentException.class, initialization, "You have to specify a number bigger than 1.");
        }

        @Test
        void openFailureWhenColIsZero() {
            Executable initialization = () -> percolation.isOpen(0, 3);
            assertThrows(IllegalArgumentException.class, initialization, "You have to specify a number bigger than 1.");
        }
    }

}