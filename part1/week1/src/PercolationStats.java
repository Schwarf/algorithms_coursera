import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


public class PercolationStats {
    private final double[] fraction;
    private final double mean;
    private final double standard_deviation;

    public PercolationStats(int size_of_grid, int number_of_trials) {
        if (size_of_grid < 1) {
            throw new IllegalArgumentException("Argument n is smaller than 1.");
        }
        if (number_of_trials < 1) {
            throw new IllegalArgumentException("Argument trials is smaller than 1.");
        }
        int total_number_of_sites = size_of_grid * size_of_grid;
        double number_of_open_sites;
        fraction = new double[number_of_trials];
        for (int i = 0; i < number_of_trials; i++) {
            Percolation percolation = new Percolation(size_of_grid);
            while (!percolation.percolates()) {
                int row = StdRandom.uniform(1, size_of_grid + 1);
                int column = StdRandom.uniform(1, size_of_grid + 1);
                if (percolation.isOpen(row, column)) continue;
                percolation.open(row, column);
            }
            number_of_open_sites = percolation.numberOfOpenSites();
            fraction[i] = number_of_open_sites / total_number_of_sites;
        }
        mean = StdStats.mean(fraction);
        standard_deviation = StdStats.stddev(fraction);

    }

    public double mean() {
        return mean;

    }

    public double stddev() {
        return standard_deviation;
    }

    public double confidenceLo() {
        double tSqrt = Math.sqrt(fraction.length);
        return (mean - (1.96 * standard_deviation / tSqrt));
    }

    public double confidenceHi() {
        double tSqrt = Math.sqrt(fraction.length);
        return mean + (1.96 * standard_deviation / tSqrt);
    }

    public static void main(String[] args) {
        int grid_size = Integer.parseInt(args[0]);
        int number_of_trials = Integer.parseInt(args[1]);

        PercolationStats percolation_stats = new PercolationStats(grid_size, number_of_trials);
        System.out.printf("Mean value is         %f %n", percolation_stats.mean());
        System.out.printf("Standard deviation is %f %n", percolation_stats.stddev());
        System.out.printf("95 confidence interval =[ %f, %f ]", percolation_stats.confidenceLo(), percolation_stats.confidenceHi());


    }
}
