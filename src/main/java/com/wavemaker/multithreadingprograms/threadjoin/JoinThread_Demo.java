package com.wavemaker.multithreadingprograms.threadjoin;

public class JoinThread_Demo implements Runnable{
    private final String threadName;
    private final int sleepTime;

    public JoinThread_Demo(String threadName, int sleepTime){
        this.threadName=threadName;
        this.sleepTime=sleepTime;
    }
    @Override
    public void run() {
        System.out.println(threadName+" starting");
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(threadName+" completed");
    }
}
