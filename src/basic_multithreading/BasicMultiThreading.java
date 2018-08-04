package basic_multithreading;

// To make a class as thread we need to implement the Runnable interface
// The logic/task to be done by the thread should be written inside the
// overridden run method
class RunnerOne implements Runnable {

    @Override
    public void run() {

        for(int i = 0 ; i < 10; i++) {
            System.out.println("Runner One :"+i);
        }
    }
}

class RunnerTwo implements Runnable {

    @Override
    public void run() {

        for(int i = 0 ; i < 10 ; i++) {
            System.out.println("Runner Two :"+i);
        }
    }
}

// Another way to define a thread class is by extending the Thread class
// To define the task to be done by the Thread class is to override the run method
class RunnerOneDash extends Thread {

    @Override
    public void run () {

        for (int i = 0 ; i < 10 ; i++) {
            System.out.println("Runner One dash : "+i);
        }
    }
}

class RunnerTwoDash extends Thread {

    @Override
    public void run() {

        for (int i = 0 ; i < 10; i++) {
            System.out.println("Runner two Dash : "+i);
        }
    }
}

public class BasicMultiThreading {

    public static void main(String[] args) throws InterruptedException {

        // To declare a thread we need to create a new Thread object and pass an
        // object of the thread class which implements the Runnable interface
        Thread one = new Thread(new RunnerOne());
        Thread two = new Thread(new RunnerTwo());

        // The thread is started by calling the start method
        one.start();
        two.start();

        // join() method call on a thread objects makes the parent thread / application thread
        // wait till when the thread is done with its execution
        one.join();
        two.join();

        // This will be printed when thread one and two are done with their execution
        // If the join is not called on thread one and two then the below statement will be
        // called even when the threads are not done and will be printed when the threads are
        // doing their tasks
        System.out.println("Threads done with their execution");

        // It's not mandatory that the threads would be run one after the another
        // in any particular sequence, rather that's a random order decided by the JVM

        // To declare the thread when we define the thread class by extending the thread class
        // We simply make an object of the thread class
        //RunnerOneDash threadOne = new RunnerOneDash();
        //RunnerTwoDash threadTwo = new RunnerTwoDash();

        // The thread is started by calling the start method
        //threadOne.start();
        //threadTwo.start();

        // The one after another thread execution is not guaranteed, it's random execution however
        // using the concept of time slicing

    }
}