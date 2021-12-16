/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private final boolean[] is_open;
    private final int virtual_top_site; // see slide 58
    private final int virtual_bottom_site; // see slide 58
    private final WeightedQuickUnionUF tiles;
    private final int largest_row_or_column;
    private int number_of_open_sites = 0;

    public Percolation(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("Square-root of lattice size smaller than 1");
        }
        largest_row_or_column = n;
        int lattice_size = n * n + 2; // add virtual top and bottom
        tiles = new WeightedQuickUnionUF(lattice_size);
        is_open = new boolean[lattice_size]; // default false
        virtual_bottom_site = lattice_size - 1;
        virtual_top_site = 0;
        is_open[virtual_top_site] = true;
        is_open[virtual_bottom_site] = true;

    }

    public void open(int row, int column) {
        check_row_column(row, column);
        int index = compute_index(row, column);
        if (!is_open[index]) {
            is_open[index] = true;
            number_of_open_sites++;
        }
        // first row tiles will be connected to top
        int smallest_row_or_column = 1;
        if (row == smallest_row_or_column) {
            tiles.union(index, virtual_top_site);
        }
        // last row tiles will be connected to bottom
        if (row == largest_row_or_column) {
            tiles.union(index, virtual_bottom_site);
        }
        // For all other tiles check left, right, top and bottom tiles

        int left_tile_index = compute_index(row, column - 1);
        int right_tile_index = compute_index(row, column + 1);
        int top_tile_index = compute_index(row - 1, column);
        int bottom_tile_index = compute_index(row + 1, column);

        if (column > smallest_row_or_column && is_open[left_tile_index]) {
            tiles.union(index, left_tile_index);
        }

        if (column < largest_row_or_column && is_open[right_tile_index]) {
            tiles.union(index, right_tile_index);
        }

        if (row > smallest_row_or_column && is_open[top_tile_index]) {
            tiles.union(index, top_tile_index);
        }

        if (row < largest_row_or_column && is_open[bottom_tile_index]) {
            tiles.union(index, bottom_tile_index);
        }


    }

    public boolean isOpen(int row, int column) {
        check_row_column(row, column);
        return is_open[compute_index(row, column)];
    }

    public boolean isFull(int row, int column) {
        check_row_column(row, column);
        return tiles.find(virtual_top_site) == tiles.find(compute_index(row, column));
    }

    public int numberOfOpenSites() {
        return number_of_open_sites;
    }

    public boolean percolates() {
        return tiles.find(virtual_top_site) == tiles.find(virtual_bottom_site);
    }

    private void check_row_column(int row, int column) {
        if (row < 1 || row > largest_row_or_column) {
            throw new IllegalArgumentException("Row out of bounds");
        }
        if (column < 1 || column > largest_row_or_column) {
            throw new IllegalArgumentException("Column out of bounds");
        }
    }

    private int compute_index(int row, int column) {

        return largest_row_or_column * (row - 1) + column;
    }

    public static void main(String[] args) {

    }
}
