import java.util.ArrayList;
import java.util.List;

public class BruteCollinearPoints {
    private int number_of_segments = 0;
    private LineSegment[] line_segments;

    public BruteCollinearPoints(Point[] points) { // finds all line segments containing 4 points

        if (points == null)
            throw new IllegalArgumentException("Points is null");
        if (check_for_null(points))
            throw new IllegalArgumentException("Point in Points is null");
        if (check_for_duplicate_points(points))
            throw new IllegalArgumentException("Points contains duplicates");
        get_line_segments(points);
    }


    private void get_line_segments(Point[] points) {
        final int size = points.length;
        List<LineSegment> segments = new ArrayList<>();
        for (int index1 = 0; index1 < size - 3; index1++) {
            for (int index2 = index1 + 1; index2 < size - 2; index2++) {
                double slope12 = points[index1].slopeTo(points[index2]);

                for (int index3 = index2 + 1; index3 < size - 1; index3++) {
                    double slope13 = points[index1].slopeTo(points[index3]);

                    for (int index4 = index3 + 1; index4 < size; index4++) {
                        double slope14 = points[index1].slopeTo(points[index4]);

                        if ((slope12 == slope13) && (slope12 == slope14)) {
                            segments.add(new LineSegment(points[index1], points[index4]));
                            number_of_segments++;
                        }

                    }
                }
            }
        }
        line_segments = new LineSegment[segments.size()];

    }

    private boolean check_for_null(Point[] points) {
        for (Point point : points) {
            if (point == null)
                return true;
        }
        return false;
    }

    private boolean check_for_duplicate_points(Point[] points) {
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length - 1; j++) {
                if (points[i].compareTo(points[j]) == 0)
                    return true;
            }
        }
        return false;
    }

    public int numberOfSegments() {
        return number_of_segments;
    }

    public LineSegment[] segments() {
        return line_segments;
    }

}
