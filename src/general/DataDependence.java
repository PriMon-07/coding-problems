package general;

public class DataDependence {
    public static long getDataDependenceSum(long n) {
        long sum = 0;
        long k = 1;

        while (k <= n) {
            // Calculate x = floor(n / k)
            long x = n / k;

            // Calculate the upper limit of k for which floor(n / k) is still x
            long maxK = n / x;

            // Add this unique x to the sum
            sum += x;

            // Move k to the next boundary
            k = maxK + 1;
        }

        return sum;
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(getDataDependenceSum(13)); // Expected output: 29
        System.out.println(getDataDependenceSum(1));  // Expected output: 1
        System.out.println(getDataDependenceSum(5));  // Expected output: 8
    }
}
