package general;

public class SlowestKey {
    public static char slowestKey(int[][] keyTimes) {
        int n = keyTimes.length;
        int maxDuration = 0;
        int maxKey = keyTimes[0][0];
        int prevTime = 0;

        for (int i = 0; i < n; i++) {
            int key = keyTimes[i][0];
            int time = keyTimes[i][1];
            int duration = time - prevTime;

            if (duration > maxDuration || (duration == maxDuration && key > maxKey)) {
                maxDuration = duration;
                maxKey = key;
            }

            prevTime = time;
        }

        return (char) (maxKey + 'a');
    }

    public static void main(String[] args) {
        int[][] keyTimes1 = {{0, 2}, {1, 3}, {0, 7}};
        System.out.println(slowestKey(keyTimes1)); // Output: a

        int[][] keyTimes2 = {{0, 1}, {0, 3}, {4, 5}, {5, 6}, {4, 10}};
        System.out.println(slowestKey(keyTimes2)); // Output: e
    }
}

