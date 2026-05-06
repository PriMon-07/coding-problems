package concurrency.threadcommunication.evenodd;

public class Counter {
    private int counter = 0;
    private boolean isEven = true;
    private int limit;

    public Counter(int limit) {
        this.limit = limit;
    }

    public synchronized void printEven() {
        while (counter < limit) {
            while (!isEven) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            if (counter < limit) {
                System.out.println(Thread.currentThread().getName() + " number: " + counter);
                counter++;
                isEven = false;
                notify();
            }
        }
    }

    public synchronized void printOdd() {
        while (counter < limit) {
            while (isEven) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            if (counter < limit) {
                System.out.println(Thread.currentThread().getName() + " number: " + counter);
                counter++;
                isEven = true;
                notify();
            }
        }
    }

}
