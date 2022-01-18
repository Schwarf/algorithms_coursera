public class FastCollinearPoints {
    private int number_of_segments = 0;
    private LineSegment[] line_segments;

    public FastCollinearPoints(Point[] points) {

    }

    private void sort_(Point[] points) {

        int end = points.length - 1;
        for (int reference_index = 0; reference_index < points.length - 3; reference_index++) {
            int start = reference_index + 1;
            quicksort_(points, start, end, reference_index);
            if ()
        }
    }

    private void quicksort_(Point[] points, int start, int end, int reference_index) {
        if (end <= start)
            return;
        int index = partition_(points, start, end, reference_index);
        quicksort_(points, start, index, reference_index);
        quicksort_(points, index + 1, end, reference_index);
    }

    private int partition_(Point[] points, int start, int end, int reference_index) {
        int index1 = start;
        int index2 = end;
        while (true) {
            while (points[reference_index].slopeTo(points[++index1]) < points[reference_index].slopeTo(points[start])) {
                if (index1 == end)
                    break;
            }
            while (points[reference_index].slopeTo(points[start]) < points[reference_index].slopeTo(points[index2--])) {
                if (index2 == start)
                    break;
            }
            if (index1 >= index2)
                break;

            swap(points, index1, index2);
        }
        swap(points, start, index2);
        return index2;
    }

    private void swap(Point[] points, int index1, int index2) {
        Point help = points[index1];
        points[index1] = points[index2];
        points[index2] = help;
    }

    public int numberOfSegments() {
        return number_of_segments;
    }

    public LineSegment[] segments() {
        return line_segments;
    }
}
