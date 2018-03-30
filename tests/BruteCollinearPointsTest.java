import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BruteCollinearPointsTest {

    @Test
    void testInitializeWithNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new BruteCollinearPoints(null));
        assertEquals("points cannot be null", exception.getMessage());
    }

    @Test
    void testInitializeWithNullPoint() {
        Point[] points = {new Point(1, 1), null, null, null};
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new BruteCollinearPoints(points));
        assertEquals("point cannot be null", exception.getMessage());
    }

    @Test
    void testInitializeWithDuplicatePoints() {
        Point[] points = {new Point(1, 1), new Point(2, 2), new Point(3, 3), new Point(1, 1)};
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new BruteCollinearPoints(points));
        assertEquals("point cannot be duplicated", exception.getMessage());
    }


}