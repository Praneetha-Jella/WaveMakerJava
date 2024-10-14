package com.wavemaker.multithreadingprograms.counter;

public class Counter {
    private  int count;

    public synchronized void increment(){
        count++;
        String str = String.format("Thread Name: %s, Count: %d",Thread.currentThread().getName(),count);
        System.out.println(str);
    }

    public int getCount(){
        return count;
    }
}
