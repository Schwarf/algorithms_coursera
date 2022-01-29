import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FastCollinearPoints {
    private LineSegment[] lineSegments;


    public FastCollinearPoints(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException("Points is null");
        if (checkForNull(points))
            throw new IllegalArgumentException("Point in Points is null");
        if (checkForDuplicatePoints(points))
            throw new IllegalArgumentException("Points contains duplicates");
        Point[] sortedPoints = points.clone();
        Arrays.sort(sortedPoints);
        final int numberOfPoints = sortedPoints.length;
        final List<LineSegment> lineSegmentList = new LinkedList<>();
        for (Point referencePoint : sortedPoints) {
            Arrays.sort(points, referencePoint.slopeOrder());
            int index2 = 1;
            while (index2 < numberOfPoints) {
                LinkedList<Point> candidates = new LinkedList<>();
                final double slopeReference = referencePoint.slopeTo(points[index2]);
                do {
                    candidates.add(points[index2++]);
                } while (index2 < numberOfPoints && referencePoint.slopeTo(points[index2]) == slopeReference);

                if (candidates.size() > 2 && referencePoint.compareTo(candidates.peek()) < 0) {
                    Point maximum = candidates.removeLast();
                    lineSegmentList.add(new LineSegment(referencePoint, maximum));

                }
            }

        }
        lineSegments = lineSegmentList.toArray(new LineSegment[0]);
    }

    private boolean checkForNull(Point[] points) {
        for (Point point : points) {
            if (point == null)
                return true;
        }
        return false;
    }

    private boolean checkForDuplicatePoints(Point[] points) {
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length - 1; j++) {
                if (points[i].compareTo(points[j]) == 0)
                    return true;
            }
        }
        return false;
    }


    public int numberOfSegments() {
        return lineSegments.length;
    }

    public LineSegment[] segments() {
        return lineSegments.clone();
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();

    }
}
