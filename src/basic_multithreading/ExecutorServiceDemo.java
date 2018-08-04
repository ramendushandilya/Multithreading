package basic_multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *      1). ExecutorService service = Executors.newCachedThreadPool();
 *          - Going to return an executor service that can dynamically reuse threads
 *          - Before starting a job, it checks whether there are any threads that finished the job and reuses them
 *          - If there are no threads waiting for a job to be assigned to them it creates a new one
 *          - It is good for the processor, it's an effective solution
 *
 *      2). ExecutorService service = Executors.newFixedThreadPool(N)
 *          - Maximise the number of threads
 *          - If we want to start a new job and all the threads are busy, we have to wait for one to terminate
 *
 *      3). ExecutorService service = Executors.newSingleThreadExecutor()
 *          - It uses a single thread for the job
 *
 *      - execute() = runnable + callable
 *      - submit()  = runnable
 */
public class ExecutorServiceDemo {

    public static void main(String[] args) {

        ExecutorService service = Executors.newCachedThreadPool();
        for(int i = 0 ; i < 5 ; i++) {
            service.submit(new AnotherWorker());
        }
    }
}

class AnotherWorker implements Runnable {

    @Override
    public void run() {

        for(int i = 0 ; i < 10 ; i++) {
            System.out.println(i);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}