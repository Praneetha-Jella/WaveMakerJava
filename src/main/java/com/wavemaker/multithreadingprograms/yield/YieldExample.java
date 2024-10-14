package com.wavemaker.multithreadingprograms.yield;

public class YieldExample {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + " - Iteration: " + i);
                Thread.yield();  // Hint to the scheduler to switch to another thread
            }
        }, "Thread-1");

        Thread t2 = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + " - Iteration: " + i);
                Thread.yield();  // Hint to the scheduler to switch to another thread
            }
        }, "Thread-2");

        t1.start();
        t2.start();
    }
}
