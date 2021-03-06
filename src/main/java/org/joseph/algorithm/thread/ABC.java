package org.joseph.algorithm.thread;

import java.util.concurrent.locks.ReentrantLock;

public class ABC {

    private static ReentrantLock lock = new ReentrantLock();
    private static Integer status = 0;

    public static void main(String[] args) {

        new Thread(() -> {
            while (true) {
                lock.lock();
                if (status % 3 == 0) {
                    System.out.printf("A");
                    status++;
                }
                lock.unlock();
            }
        }).start();

        new Thread(() -> {
            while (true) {
                lock.lock();
                if (status % 3 == 1) {
                    System.out.printf("B");
                    status++;
                }
                lock.unlock();
            }
        }).start();

        new Thread(() -> {
            while (true) {
                lock.lock();
                if (status % 3 == 2) {
                    System.out.printf("C");
                    status++;
                }
                lock.unlock();
            }
        }).start();

    }




}
