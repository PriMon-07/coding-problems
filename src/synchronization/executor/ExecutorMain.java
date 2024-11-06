package synchronization.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorMain {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Callable<Integer> callable1 = () -> {
            Thread.sleep(1000);
            System.out.println("Callable - 1");
            throw new Exception("Hi");
//            return 1;
        };
        Callable<Integer> callable2 = () -> {
            Thread.sleep(1000);
            System.out.println("Callable - 1");
            return 1;
        };

        Future<Integer> future = executorService.submit(callable1);
        try {
            System.out.println(future.isDone());
            System.out.println(future.isCancelled());
            Integer i = future.get();
            System.out.println(future.isDone());
            System.out.println(future.isCancelled());
            System.out.println("result: " + i);
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(future.isDone());
            System.out.println(future.isCancelled());
            e.printStackTrace();
        }
        executorService.shutdown();
    }
}
