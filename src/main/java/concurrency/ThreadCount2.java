package concurrency;

public class ThreadCount2 extends Thread {
    private Count count;
    public ThreadCount2(Count count) {
        this.count = count;
    }

    @Override
    public void run() {
        count.incrementCount();
    }
}
