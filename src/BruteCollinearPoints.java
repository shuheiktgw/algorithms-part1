import javax.swing.text.Segment;
import java.util.concurrent.atomic.DoubleAccumulator;

public class BruteCollinearPoints {

    private LineSegment[] segments;
    private int index;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points){
        if (points == null) {
            throw new IllegalArgumentException("points cannot be null");
        }

        segments = new LineSegment[points.length/4];
        index = 0;

        for (int i = 0; i < points.length - 3; i++) {
            Point p1 = points[i];
            checkPoint(p1);

            for (int l = i + 1; l < points.length - 2; l++) {
                Point p2 = points[l];

                checkPoint(p2);

                Double p1ToP2 = p1.slopeTo(p2);

                checkSlope(p1ToP2);

                for (int m = l + 1; m < points.length - 1; m++) {
                    Point p3 = points[m];

                    checkPoint(p3);

                    Double p1ToP3 = p1.slopeTo(p3);

                    checkSlope(p1ToP3);

                    if (!p1ToP2.equals(p1ToP3)){
                        continue;
                    }

                    for (int n = m + 1; n < points.length; n++) {
                        Point p4 = points[n];

                        checkPoint(p4);

                        Double p1ToP4 = p1.slopeTo(p4);

                        checkSlope(p1ToP4);

                        if (!p1ToP2.equals(p1ToP4)){
                            continue;
                        }

                        segments[index] = new LineSegment(p1, p4);
                        index++;
                    }
                }
            }
        }
    }

    // the number of line segments
    public int numberOfSegments(){
        return index;
    }

    // the line segments
    public LineSegment[] segments() {
        return segments;
    }

    private void checkPoint(Point point) {
        if (point == null) {
            throw new IllegalArgumentException("point cannot be null");
        }
    }

    private void checkSlope(Double slope) {
        if (slope == Double.NEGATIVE_INFINITY) {
            throw new IllegalArgumentException("point cannot be duplicated");
        }
    }
}
