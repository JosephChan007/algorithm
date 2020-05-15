package org.joseph.algorithm.thread;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchDemo {


    static class Customer implements Runnable {

        private CountDownLatch latch;
        private String name;

        public Customer(CountDownLatch latch, String name) {
            this.latch = latch;
            this.name = name;
        }

        @Override
        public void run() {

            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");

            System.out.println(sdf.format(new Date()) + " : " + name + " 出发去饭店");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(sdf.format(new Date()) + " : " + name + " 到了饭店");
            latch.countDown();

        }
    }


    static class Waiter implements Runnable {

        private CountDownLatch latch;
        private String name;

        public Waiter(CountDownLatch latch, String name) {
            this.latch = latch;
            this.name = name;
        }

        @Override
        public void run() {

            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
            System.out.println(sdf.format(new Date()) + " : " + name + " 等待顾客");
            try {
                latch.await();
//                latch.await(10, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(sdf.format(new Date()) + " : " + name + " 开始上菜");
        }
    }


    public static void main(String[] args) {

        CountDownLatch latch = new CountDownLatch(3);
        List<Thread> threads = Arrays.asList(
                new Thread(new Customer(latch, "小王")),
                new Thread(new Customer(latch, "小章")),
                new Thread(new Customer(latch, "小吴"))
        );

        for (Thread thread : threads) {
            thread.start();
        }

        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(new Waiter(latch, "小芳")).start();

    }

}
