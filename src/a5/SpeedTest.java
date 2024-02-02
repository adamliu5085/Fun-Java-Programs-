package a5;

import java.awt.Color;
import java.io.IOException;

/**
 * Measures and plots the time to add N elements to a DynamicArray compared to
 * DoublingDynamicArray
 * 
 * When this file is run, the plot should be displayed on one's screen
 * 
 * @author David Johnson
 * @author Erin Parker
 * @author Travis Martin
 *
 *         DO NOT MODIFY THIS FILE
 */
public class SpeedTest {
    /**
     * Returns the seconds required to add n elements to a DynamicArray.
     */
    public static double addNToDynamic(int n) {
        long startTime = System.nanoTime();
        DynamicArray array = new DynamicArray();
        for (int i = 0; i < n; i++) {
            array.add("" + i);
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000_000.0;
    }

    /**
     * Returns the seconds required to add n elements to a DoublingDynamicArray.
     */
    public static double addNToDoublingDynamic(int n) {
        long startTime = System.nanoTime();
        DoublingDynamicArray array = new DoublingDynamicArray();
        for (int i = 0; i < n; i++) {
            array.add("" + i);
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000_000.0;
    }

    public static void main(String[] args) throws IOException {
        // Set the desired number of iterations
        int lowerBound = 100;
        int step = 100;
        int iterations = 31;

        // Declare arrays to store our data
        double[] arraySizes = new double[iterations];
        double[] dynamicArrayTimes = new double[iterations];
        double[] doublingDynamicArrayTimes = new double[iterations];

        // Time the operations
        for (int i = 0; i < iterations; i++) {
            int n = lowerBound + step * i;
            arraySizes[i] = n;
            System.out.println("Timing arrays of size: " + n);
            doublingDynamicArrayTimes[i] = addNToDoublingDynamic(n);
            dynamicArrayTimes[i] = addNToDynamic(n);
        }

        Plot plot = Plot
                .plot(Plot.plotOpts().title("Time (seconds) to add n items for two dynamic array implementations")
                        .legend(Plot.LegendFormat.TOP))
                .xAxis("n", null).yAxis("seconds", null)
                .series("DynamicArray", Plot.data().xy(arraySizes, dynamicArrayTimes),
                        Plot.seriesOpts().color(Color.RED).marker(Plot.Marker.DIAMOND))
                .series("DoublingDynamicArray", Plot.data().xy(arraySizes, doublingDynamicArrayTimes),
                        Plot.seriesOpts().color(Color.BLUE).marker(Plot.Marker.CIRCLE));
        plot.show();
    }
}
