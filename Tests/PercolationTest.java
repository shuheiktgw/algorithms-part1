
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

        for(int i = 0; i < 9; i++) {
            assertEquals(i, id[i]);
            assertEquals(0, status[i]);
        }
    }

    @Test
    void initializeFailure() {
        Executable initialization = () -> new Percolation(0);
        assertThrows(IllegalArgumentException.class, initialization, "You have to specify a number bigger than 1.");
    }
}