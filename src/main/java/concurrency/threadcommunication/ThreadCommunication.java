package concurrency.threadcommunication;

public class ThreadCommunication {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();
        Producer producer = new Producer(sharedResource);
        Consumer consumer = new Consumer(sharedResource);
        Thread thread1 = new Thread(producer, "Producer-Thread-0");
        Thread thread2 = new Thread(consumer, "Consumer-Thread-0");

        thread1.start();
        thread2.start();
    }
}
