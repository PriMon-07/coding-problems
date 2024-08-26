package general;

import java.util.*;

public class Test {

    private static int getTensDigit(int number) {
        return Math.abs(number) / 10 % 10;
    }

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> counter = new HashMap<>();
        for (int n : nums) {
            counter.put(n, counter.getOrDefault(n, 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> heap = new PriorityQueue<>(
                (a, b) -> Integer.compare(b.getValue(), a.getValue())
        );

        for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            heap.offer(entry);
        }

        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = Objects.requireNonNull(heap.poll()).getKey();
        }

        return res;
    }

    public static void numbersAfterSorting(int[] arr, int n) {
        Integer[] numbers = Arrays.stream(arr).boxed().toArray(Integer[]::new);

        Arrays.sort(numbers, (a, b) -> {
            int tensDigitA = getTensDigit(a);
            int tensDigitB = getTensDigit(b);

            if (tensDigitA != tensDigitB) {
                return Integer.compare(tensDigitA, tensDigitB);
            } else {
                return Integer.compare(b, a);
            }
        });

        for (int i = 0; i < n; i++) {
            System.out.print(numbers[i]);
            if (i < n - 1) {
                System.out.print(" ");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }

        numbersAfterSorting(array, n);
    }
}