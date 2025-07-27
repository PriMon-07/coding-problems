package concurrency;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Count count = new Count();
        ThreadCount t1 = new ThreadCount(count);
        ThreadCount t2 = new ThreadCount(count);
        ThreadCount2 t3 = new ThreadCount2(count);


        t3.start();
        t1.start();
        t2.start();

        t1.join();
        t2.join();
        t3.join();

        count.getCount();


    }

}
