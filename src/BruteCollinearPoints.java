public class BruteCollinearPoints {

    // finds all line segments containing 4 points
//    バリデーションの追加から,同値のポイントはslopeの計算から検知できるよ
    public BruteCollinearPoints(Point[] points){
        if (points == null) {
            throw new IllegalArgumentException("points cannot be null");
        }


        for (int i = 0; i < points.length - 3; i++) {
            Point p1 = points[i];

            if (p1 == null) {
                throw new IllegalArgumentException("point cannot be null");
            }

            for (int l = i + 1; l < points.length - 2; l++) {
                Point p2 = points[l];

                if (p2 == null) {
                    throw new IllegalArgumentException("point cannot be null");
                }

                Double p1ToP2 = p1.slopeTo(p2);

                for (int m = l + 1; m < points.length - 1; m++) {
                    Point p3 = points[m];
                    Double p1ToP3 = p1.slopeTo(p3);

                    if (!p1ToP2.equals(p1ToP3)){
                        continue;
                    }

                    for (int n = m + 1; n < points.length; n++) {
                        Point p4 = points[n];
                        Double p1ToP4 = p1.slopeTo(p4);

                        if (!p1ToP2.equals(p1ToP4)){
                            continue;
                        }


                    }
                }
            }
        }
    }

    // the number of line segments
    public int numberOfSegments(){
        return 0;
    }

    // the line segments
    public LineSegment[] segments() {
        return null;
    }
}
