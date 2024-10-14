package com.wavemaker.multithreadingprograms.defineathread.implementsRunnable;

public class MyRunnableClass implements Runnable{
    @Override
    public void run() {
        for(int i = 0 ; i < 5; i++){
            System.out.println("Child thread"+Thread.currentThread().getName());
        }
    }
}
