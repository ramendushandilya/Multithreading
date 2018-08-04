package basic_multithreading;

import java.util.ArrayList;
import java.util.List;

class Helper {

    private List<Integer> list = new ArrayList<>();
    private static final int LIMIT = 5;
    private static final int BOTTOM = 0;
    private final Object lock = new Object();
    private int value = 0;

    public void producer() throws InterruptedException{

        synchronized (lock) {
            while (true) {
                if(list.size() == LIMIT) {
                    System.out.println("Waiting for consumer to consume items");
                    lock.wait();
                } else { // 0 1 2 3 4
                    System.out.println("Producing item "+value);
                    list.add(value);
                    value++;
                    lock.notify();
                }
                Thread.sleep(400);
            }
        }
    }

    public void consumer() throws InterruptedException {

        synchronized (lock) {
            while (true) {
                if(list.size() == BOTTOM) {
                    System.out.println("Waiting for producer to produce items");
                    lock.wait();
                } else { // 4 3 2 1 0
                    System.out.println("Consumed "+ list.remove(--value));
                    lock.notify();
                }
                Thread.sleep(400);
            }
        }
    }
}

public class ProducerConsumer {



    public static void main(String[] args) {

        Helper helper = new Helper();

        Thread one = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    helper.producer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread two = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    helper.consumer();
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