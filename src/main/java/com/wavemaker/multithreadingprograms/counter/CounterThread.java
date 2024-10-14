package com.wavemaker.multithreadingprograms.counter;

public class CounterThread implements Runnable{
    Counter counter = new Counter();
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" started");

        for(int i = 0 ; i < 5; i++){
            counter.increment();
        }
        System.out.println("The total final count of "+Thread.currentThread().getName()+" would be: "+counter.getCount());
    }
}
