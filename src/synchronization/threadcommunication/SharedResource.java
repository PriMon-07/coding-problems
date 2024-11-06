package synchronization.threadcommunication;

public class SharedResource {
    int data;
    boolean hasData;

    public synchronized void produce(int value) {
        while (hasData) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        data = value;
        hasData = true;
        System.out.println("Thread: " + Thread.currentThread().getName() + " produced: " + data);
        notify();
    }

    public synchronized void consume() {
        while (!hasData) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Thread: " + Thread.currentThread().getName() + " consumed: " + data);
        hasData = false;
        notify();
    }
}
