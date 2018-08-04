package basic_multithreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Reentrant Lock has the same behavior as synchronised blocks
 * new ReentrantLock(boolean fairnessParameter)
 * fairnessParameter when true the longest waiting thread will get the lock
 * when set to false there is no access order
 * While using reentrant lock we should use try catch block when doing critical section
 * lock should be unlocked in the finally block, it may be unlocked from any place in any other method in the class
 */
public class ReentrantLockDemo {

    private static int counter = 0;
    private static Lock lock = new ReentrantLock();

    private static void increment() {

        lock.lock();
        try {
            for(int i = 0 ; i < 10000 ; i++) {
                counter++;
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {

        Thread one = new Thread(new Runnable() {
            @Override
            public void run() {
                increment();
            }
        });

        Thread two = new Thread(new Runnable() {
            @Override
            public void run() {
                increment();
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

        System.out.println("Counter value = "+counter);
    }
}