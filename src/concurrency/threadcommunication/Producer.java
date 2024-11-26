package concurrency.threadcommunication;

public class Producer implements Runnable {
    private final SharedResource sharedResource;
    public Producer(SharedResource sharedResource) {
        this.sharedResource = sharedResource;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10 ; i++) {
            sharedResource.produce(i);
        }
    }
}
