package org.joseph.algorithm.thread;

import java.util.concurrent.locks.ReentrantLock;

public class ABC1 {

    private static ReentrantLock lock = new ReentrantLock();
    private static Integer count = 0;

    private static void print(String out, Integer status, Integer val) {
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
        new Thread(() -> {
            print("A", 3, 0);
        }).start();

        new Thread(() -> {
            print("B", 3, 1);
        }).start();

        new Thread(() -> {
            print("C", 3, 2);
        }).start();
    }

}
