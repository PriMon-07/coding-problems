package general;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AmazonDispatch {

    public static int getMaximumCredits(int[] inventory, int dispatch1, int dispatch2, int skips) {
        int n = inventory.length;
        List<Integer> skipNeeds = new ArrayList<>();
        int credits = 0;

        for (int inv : inventory) {
            long totalCycle = (long) dispatch1 + dispatch2;

            long cycles = inv / totalCycle;
            long rem = inv % totalCycle;

            boolean naturalWin;
            if (rem == 0) {
                // Last hit naturally by co-worker
                naturalWin = false;
            } else if (rem <= dispatch1) {
                // You win without skipping
                naturalWin = true;
            } else {
                naturalWin = false;
            }

            if (naturalWin) {
                credits++;
            } else {
                // How many skips needed for you to win?
                long remaining = (rem == 0) ? totalCycle : rem;
                long neededDamage = remaining - dispatch1;
                long skipsNeeded = (neededDamage + dispatch1 - 1) / dispatch1;
                skipNeeds.add((int) skipsNeeded);
            }
        }

        // Sort by least skips needed
        Collections.sort(skipNeeds);

        // Spend skips on easiest warehouses to win
        for (int s : skipNeeds) {
            if (skips >= s) {
                skips -= s;
                credits++;
            } else {
                break;
            }
        }

        return credits;
    }

    public static void main(String[] args) {
        int[] inventory = {10, 6, 12, 8, 15, 1};
        int dispatch1 = 2;
        int dispatch2 = 3;
        int skips = 3;

        int result = getMaximumCredits(inventory, dispatch1, dispatch2, skips);
        System.out.println(result); // Expected output: 5
    }
}

