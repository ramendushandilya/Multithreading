package basic_multithreading;

class Worker implements Runnable {

    // Every variable is cached to enhance the performance
    // But when we want that a variable is read from the RAM not from the cache we use the
    // volatile keyword
    // volatile keyword forces java to read the variable from the RAM not from the cache
    // Caches are a major performance enhancer so when unless it's not totally necessary
    // we shouldn't be using the volatile keyword or else the performance will be degraded
    private volatile boolean isTerminated = false;

    @Override
    public void run() {

        while (!isTerminated) {
            System.out.println("Worker doing its work...");
        }
    }

    public boolean isTerminated() {
        return isTerminated;
    }

    public void setTerminated(boolean terminated) {
        isTerminated = terminated;
    }
}

public class VolatileDemo {

    public static void main(String[] args) throws InterruptedException {

        Worker worker = new Worker();
        Thread one = new Thread(worker);
        one.start();
        one.sleep(300);
        worker.setTerminated(true);
        System.out.println("Worker done with its work");
    }
}