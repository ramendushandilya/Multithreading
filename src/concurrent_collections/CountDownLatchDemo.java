package concurrent_collections;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This is used to synchronise one or more tasks by forcing them to wait for the completion of a set of operations
 * being performed by the other tasks
 * You give an initial count to the count down latch object and any task that calls await() on that object will block
 * until the count reaches zero
 *
 * Other tasks may call countDown() on the object to reduce the count once a task is finished
 * A countDownLatch is designed to be used in one shot, the count can't be reset
 *
 * If you need a version that resets the count you may use cyclic barrier
 *
 *
 */

class Worker implements Runnable{

    private int id;
    private CountDownLatch latch;
    private Random random;

    public Worker(int id, CountDownLatch latch) {
        this.id = id;
        this.latch = latch;
        this.random = new Random();
    }

    public void doWork() {
        System.out.println("Thread with id :"+this.id+" starts working...");
        try {
            Thread.sleep(random.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        doWork();
        latch.countDown();
    }
}

public class CountDownLatchDemo {

    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        CountDownLatch latch = new CountDownLatch(5);
        for(int i = 0 ; i < 5 ; i++) {
            service.execute(new Worker(i, latch));
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("All pre requisites are done");
        service.shutdown();
    }
}