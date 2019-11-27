package org.joseph.algorithm.thread;


public class A extends Thread {

    {
        System.out.println("Thread AA");
    }

    public A() {
        this.setName("Thread A");
        pringThread();
    }

    private void pringThread() {
        System.out.println(Thread.currentThread().getName());
    }
}
