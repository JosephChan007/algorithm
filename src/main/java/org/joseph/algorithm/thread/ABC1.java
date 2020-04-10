package org.joseph.algorithm.thread;

import java.util.concurrent.locks.ReentrantLock;

public class ABC1 {

    private static ReentrantLock lock = new ReentrantLock();
    private static Integer count = 0;

    private void print(String out, Integer status, Integer val) {
        while (true) {
            lock.lock();
            if (count % status == val) {
                System.out.print(out);
                count++;
            }
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ABC1 abc1 = new ABC1();
        new Thread(() -> {
            abc1.print("A", 3, 0);
        }).start();

        new Thread(() -> {
            abc1.print("B", 3, 1);
        }).start();

        new Thread(() -> {
            abc1.print("C", 3, 2);
        }).start();


        /* 非常具有迷惑性的多线程非常规写法

        new Thread(() -> {
            System.out.println("aaa");
        }) {
            @Override
            public void run() {
                System.out.println("bbb");
            }
        }.start();

        */


    }

}
