package org.joseph.algorithm.thread;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicbarrierDemo {

    static class Customer implements Runnable {

        private CyclicBarrier barrier;
        private String name;

        public Customer(CyclicBarrier barrier, String name) {
            this.barrier = barrier;
            this.name = name;
        }

        @Override
        public void run() {

            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");

            System.out.println(sdf.format(new Date()) + " : " + name + " 到了饭店");
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(sdf.format(new Date()) + " : " + name + " 开始吃饭...");

        }
    }


    static class Waiter implements Runnable {

        private String name;

        public Waiter(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
            System.out.println(sdf.format(new Date()) + ": 顾客到齐，" + name + " 开始上菜");
        }
    }


    public static void main(String[] args) {

        CyclicBarrier barrier = new CyclicBarrier(3, new Waiter("小芳"));
        List<Thread> threads = Arrays.asList(
                new Thread(new Customer(barrier, "小王")),
                new Thread(new Customer(barrier, "小章")),
                new Thread(new Customer(barrier, "小吴"))
        );

        for (Thread thread : threads) {
            thread.start();
        }

    }




}
