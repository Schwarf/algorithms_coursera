public class FastCollinearPoints {
    private int number_of_segments = 0;
    private LineSegment[] line_segments;

    public FastCollinearPoints(Point[] points) {
        
    }

    public int numberOfSegments() {
        return number_of_segments;
    }

    public LineSegment[] segments() {
        return line_segments;
    }
}
