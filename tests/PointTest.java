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
    void testSlopeTo() {
        Point another = new Point(5, 5);

        assertEquals(1.0, point.slopeTo(another));
    }

    @Test
    void testSlopeToWithTheSamePoint() {
        Point another = new Point(1, 1);

        assertEquals(Double.NEGATIVE_INFINITY, point.slopeTo(another));
    }

    @Test
    void testSlopeToWithTheSameX() {
        Point another = new Point(1, 2);

        assertEquals(Double.POSITIVE_INFINITY, point.slopeTo(another));
    }

    @Test
    void testCompareToWithBiggerYAndBiggerX() {
        Point another = new Point(2, 2);

        assertTrue(point.compareTo(another) < 0);
    }

    @Test
    void testCompareToWithBiggerYAndSmallerX() {
        Point another = new Point(2, 0);

        assertTrue(point.compareTo(another) < 0);
    }

    @Test
    void testCompareToWithSmallerYAndBiggerX() {
        Point another = new Point(0, 2);

        assertTrue(point.compareTo(another) > 0);
    }

    @Test
    void testCompareToWithSmallerYAndSmallerX() {
        Point another = new Point(0, 0);

        assertTrue(point.compareTo(another) > 0);
    }

    @Test
    void testCompareToWithTheSame() {
        Point another = new Point(1, 1);

        assertEquals(0, point.compareTo(another));
    }

    @Test
    void testSlopeOrder() {
        Point another = new Point(1, 1);

        assertEquals(0, point.compareTo(another));
    }

}