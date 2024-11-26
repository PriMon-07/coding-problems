package concurrency;

public class ThreadCount extends Thread {
    private Count count;
    public ThreadCount(Count count) {
        this.count = count;
    }

    @Override
    public void run() {
        count.increment();
    }
}
