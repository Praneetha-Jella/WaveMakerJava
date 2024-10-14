package com.wavemaker.multithreadingprograms.defineathread.extendsThread;

public class MyThread extends Thread{
    @Override
    public void run(){
        for(int i = 0 ; i < 10 ; i++){
            System.out.println("Hi There I am child thread!");
        }
    }
}
