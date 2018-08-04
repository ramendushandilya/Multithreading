package basic_multithreading;

class Processor {
    public void produce() throws InterruptedException{

        synchronized (this) {
            System.out.println("Hi from the producer method");
            // wait method makes a thread wait for another thread to run
            // we can specify an amount of time a thread waits for another thread
            wait();
            System.out.println("Hi back from the producer method again!!");
        }
    }

    public void consume() throws InterruptedException{
        Thread.sleep(1000);
        synchronized (this) {
            System.out.println("In the consumer method");
            // Once the present thread is done it tells another thread(s) to resume by calling notify or notifyAll()
            notify();
            // Even if the notify is called, the remaining tasks for a thread will be done before the other thread resumes
            System.out.println("I will be printed even after the notify is called");
        }
    }
}

public class WaitNotifyDemo {

    public static void main(String[] args) {
        Processor processor = new Processor();

        Thread one = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread two = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
    }
}