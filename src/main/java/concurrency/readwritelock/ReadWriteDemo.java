package concurrency.readwritelock;

public class ReadWriteDemo {
    public static void main(String[] args) {
        ReadWriteCounter counter = new ReadWriteCounter();

        // Multiple reader threads
        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                for (int j = 0; j < 3; j++) {
                    counter.getValue();
                    try { Thread.sleep(200); } catch (InterruptedException e) {}
                }
            }, "Reader-" + i).start();
        }

        // Writer threads
        new Thread(() -> {
            counter.setValue(100);
            try { Thread.sleep(2000); } catch (InterruptedException _) {}
            counter.setValue(200);
        }, "Writer-1").start();

        new Thread(() -> {
            try { Thread.sleep(1000); } catch (InterruptedException _) {}
            for (int i = 0; i < 3; i++) {
                counter.increment();
                try { Thread.sleep(800); } catch (InterruptedException _) {}
            }
        }, "Writer-2").start();
    }
}
