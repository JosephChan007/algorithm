package org.joseph.algorithm.thread;


public class B extends A {

    {
        System.out.println("Thread BB");
    }


    public B() {
        System.out.println("Thread B");
    }

    public static void main(String[] args) {
        Thread.currentThread().setName("Thread main");
        Thread t = new B();
        t.start();
        System.out.println(1<<4);

    }


}
