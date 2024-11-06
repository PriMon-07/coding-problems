package synchronization;

public class Count {
    private int count = 0;
    private int count2 = 0;
    public void increment() {
        System.out.println("Hi");
        for (int i = 0; i < 1000; i++) {
            count++;
        }
        System.out.println("Mid Hi");
        synchronized (this) {
            for (int i = 0; i < 1000; i++) {
                count2++;
            }
        }
        System.out.println("Bye");
    }

    public synchronized void incrementCount() {
        System.out.println("Hi incrementCount");
        for (int i = 0; i < 1000; i++) {
            count++;
        }
        System.out.println("Bye incrementCount");
    }
    public void getCount() {
        System.out.println(count);
        System.out.println(count2);
    }
}
