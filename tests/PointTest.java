import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {
    private Point point;

    @BeforeEach
    void setup(){
        point = new Point(1, 1);
    }

    @Test
    void testSlopeToWithIntSlope() {
        Point another = new Point(5, 5);

        assertEquals(1.0, point.slopeTo(another));
    }

    @Test
    void testSlopeToWithDoubleSlope() {
        Point another = new Point(3, 4);

        assertEquals(1.5, point.slopeTo(another));
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
        Point[] points = new Point[3];
        points[0] = new Point(2, 0);
        points[1] = new Point(2, 5);
        points[2] = new Point(2, 3);

        Arrays.sort(points, point.slopeOrder());

        assertEquals("(2, 0)", points[0].toString());
        assertEquals("(2, 3)", points[1].toString());
        assertEquals("(2, 5)", points[2].toString());
    }

}