package basic_multithreading;

/**
 * Using a synchronised method is not a good idea when two threads are working one blocks which are independent
 * In that case one thread could be working on a block independently and other one could do so too
 * In that case we should go for synchronised blocks
 * If we simply use the synchronised key word then the the class intrinsic lock would be acquired which is again same
 * as synchronized method
 * So we should be acquiring different locks
 */
public class SynchronisedBlocksDemo {

    private static int countOne = 0;
    private static int countTwo = 0;

    // Creating a lock object
    private static Object lockOne = new Object();
    private static Object lockTwo = new Object();

    private static void addOne() {
        synchronized (lockOne) {
            countOne++;
        }
    }

    private static void addTwo() {
        synchronized (lockTwo) {
            countTwo++;
        }
    }

    private static void compute() {
        for(int i = 0 ; i < 100 ; i++) {
            addOne();
            addTwo();
        }
    }

    public static void main(String[] args) {

        Thread one = new Thread(new Runnable() {
            @Override
            public void run() {
                compute();
            }
        });

        Thread two = new Thread(new Runnable() {
            @Override
            public void run() {
                compute();
            }
        });

        one.start();
        two.start();

        try {
            one.join();
            two.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Count One = "+countOne+" Count Two = "+countTwo);
    }
}