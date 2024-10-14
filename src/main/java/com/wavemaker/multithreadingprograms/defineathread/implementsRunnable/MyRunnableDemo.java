package com.wavemaker.multithreadingprograms.defineathread.implementsRunnable;

public class MyRunnableDemo {
    public static void main(String [] args){
        MyRunnableClass myRunnableClass = new MyRunnableClass();
        Thread t = new Thread(myRunnableClass);
        t.start();
        for(int i = 0 ; i < 5; i++){
            System.out.println("Main Thread"+Thread.currentThread().getName());
        }
    }
}
