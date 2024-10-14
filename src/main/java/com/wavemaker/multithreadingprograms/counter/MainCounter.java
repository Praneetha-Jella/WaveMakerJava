package com.wavemaker.multithreadingprograms.counter;

public class MainCounter {
 public static void main(String [] args) throws InterruptedException {
     Runnable myRunnable = new CounterThread();
     Runnable yourRunnable = new CounterThread();

     Thread thread1 = new Thread(myRunnable);
     Thread thread2 = new Thread(yourRunnable);

     thread1.start();
     thread2.start();

     thread1.join();
     thread2.join();
 }
}
