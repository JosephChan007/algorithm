package org.joseph.algorithm.thread;

public class AB {

    private static Object lock = new Object();
    private static Integer state = 0;
    private static Integer total = 100;


    public static void main(String[] args) {

        new Thread(() -> {
            while (state <= total) {
                synchronized (lock) {
                    lock.notify();
                    if (state % 2 == 0) {
                        System.out.print("A");
                        state++;
                    }
                    try {
                        lock.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(() -> {
            while (state <= total) {
                synchronized (lock) {
                    lock.notify();
                    if (state % 2 == 1) {
                        System.out.print("B");
                        state++;
                    }
                    try {
                        lock.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }


}
