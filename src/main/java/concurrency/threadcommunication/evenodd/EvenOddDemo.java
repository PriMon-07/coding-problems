package concurrency.threadcommunication.evenodd;

public class EvenOddDemo {
    public static void main(String[] args) {
        Counter counter = new Counter(10);

        Thread evenThread = new Thread(() -> {
            counter.printEven();
        }, "Even-Thread");

        Thread oddThread = new Thread(() -> {
            counter.printOdd();
        }, "Odd-Thread");

        evenThread.start();
        oddThread.start();
    }
}
