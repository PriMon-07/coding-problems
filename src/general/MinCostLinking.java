package general;

public class MinCostLinking {

    public static int solve(String s, String t) {
        int n = s.length();
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = i;
        }
        int[] bestPermutation = p.clone();
        int minCost = Integer.MAX_VALUE;

        do {
            if (isValidPermutation(p)) {
                int cost = calculateCost(s, t, p);
                if (cost < minCost) {
                    minCost = cost;
                    bestPermutation = p.clone();
                }
            }
        } while (nextPermutation(p));
        return minCost;
    }

    private static int calculateCost(String s, String t, int[] p) {
        int cost = 0;
        for (int i = 0; i < s.length(); i++) {
            cost += Math.abs(s.charAt(i) - t.charAt(p[i]));
        }
        return cost;
    }

    private static boolean isValidPermutation(int[] p) {
        for (int i = 0; i < p.length; i++) {
            if (Math.abs(i - p[i]) > 5) {
                return false;
            }
        }
        return true;
    }

    private static boolean nextPermutation(int[] p) {
        int i = p.length - 2;
        while (i >= 0 && p[i] >= p[i + 1]) {
            i--;
        }
        if (i < 0) {
            return false;
        }
        int j = p.length - 1;
        while (p[j] <= p[i]) {
            j--;
        }
        swap(p, i, j);
        reverse(p, i + 1, p.length - 1);
        return true;
    }

    private static void swap(int[] p, int i, int j) {
        int temp = p[i];
        p[i] = p[j];
        p[j] = temp;
    }

    private static void reverse(int[] p, int i, int j) {
        while (i < j) {
            swap(p, i, j);
            i++;
            j--;
        }
    }

    public static void main(String[] args) {
        String s = "aaaaagaaaaa";
        String t = "daaaaaaaaaz";
        System.out.println(solve(s, t)); // Output: 3
    }
}

