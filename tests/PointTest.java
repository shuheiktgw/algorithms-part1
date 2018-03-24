import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {
    private Point point;

    @BeforeEach
    void setup(){
        point = new Point(1, 1);
    }

    @Test
    void slopeTo() {
        Point another = new Point(5, 5);

        assertEquals(1.0, point.slopeTo(another));
    }

    @Test
    void slopeToWithTheSamePoint() {
        Point another = new Point(1, 1);

        assertEquals(Double.NEGATIVE_INFINITY, point.slopeTo(another));
    }

    @Test
    void slopeToWithTheSameX() {
        Point another = new Point(1, 2);

        assertEquals(Double.POSITIVE_INFINITY, point.slopeTo(another));
    }

    @Test
    void compareToWithBiggerYAndBiggerX() {
        Point another = new Point(2, 2);

        assertTrue(point.compareTo(another) < 0);
    }

    @Test
    void compareToWithBiggerYAndSmallerX() {
        Point another = new Point(2, 0);

        assertTrue(point.compareTo(another) < 0);
    }

    @Test
    void compareToWithSmallerYAndBiggerX() {
        Point another = new Point(0, 2);

        assertTrue(point.compareTo(another) > 0);
    }

    @Test
    void compareToWithSmallerYAndSmallerX() {
        Point another = new Point(0, 0);

        assertTrue(point.compareTo(another) > 0);
    }

    @Test
    void compareToWithTheSame() {
        Point another = new Point(1, 1);

        assertEquals(0, point.compareTo(another));
    }

}