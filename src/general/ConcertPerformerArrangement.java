package general;

public class ConcertPerformerArrangement {

    public static void rearrangePerformers(int n, int[] a) {
        // Traverse through the array, swapping adjacent pairs
        for (int i = 1; i < n; i += 2) {
            // Swap performers[i] with performers[i-1] to create the wave pattern
            int temp = a[i];
            a[i] = a[i - 1];
            a[i - 1] = temp;
        }
    }

    public static void main(String[] args) {
        // Example input
        int[] performers = {1, 2, 3, 4, 5};
        int n = 5;

        // Function call to rearrange performers
        rearrangePerformers(n, performers);

        // Output the rearranged list
        for (int i : performers) {
            System.out.print(i + " ");
        }
    }
}
