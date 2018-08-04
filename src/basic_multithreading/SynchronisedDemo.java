package basic_multithreading;

public class SynchronisedDemo {

    private static int counter = 0;

    // When the increment operation is not synchronised the result of the counter variable might not be consistent
    // Since the increment operation is not an atomic operation, when a variable is being incremented, another thread
    // might take hold of the variable and increment it even when it wasn't updated by the first thread in the first place
    // To prevent that from happening, the atomic operation blocks should be made synchronised so that thread takes hold
    // of the variables in appropriate state and thus the value of the variable is always consistent
    public static synchronized void increment() {
        for(int i = 0 ; i < 10000 ; i++) {
            counter++;
        }
    }

    public static void process() throws InterruptedException {

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

        one.join();
        two.join();
        System.out.printf("Counter value : "+counter);
    }

    public static void main(String[] args) throws InterruptedException {

        process();
    }
}