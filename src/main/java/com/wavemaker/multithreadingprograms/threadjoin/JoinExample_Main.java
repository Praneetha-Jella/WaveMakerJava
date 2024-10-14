package com.wavemaker.multithreadingprograms.threadjoin;

public class JoinExample_Main {
    public static void main(String [] args) throws InterruptedException {
        Runnable myRunnable = new JoinThread_Demo("My Thread",2000);
        Runnable yourRunnable = new JoinThread_Demo("Your Thread",1000);

        Thread myThread = new Thread(myRunnable);
        Thread yourThread = new Thread(yourRunnable);

        myThread.start();
        yourThread.start();

        //Using join method here, so the current thread waits until the execution of both the threads is complete.
        myThread.join();
        yourThread.join();

        System.out.println("Both threads finished, Main thread resuming");
    }
}
