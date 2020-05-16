package org.joseph.algorithm.thread;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 1.两个线程，一个线程生产者，一个线程是消费者
 * 2.生产者生产票，超过10张就休息，被消费了就继续生产。
 * 3.消费者消费票，票没了之后就休息，有票了接着消费。
 */
public class TicketProducerConsumer {

    static class Ticket {
        private int id;

        public Ticket(int id) {
            this.id = id;
        }
    }

    static class producer implements Runnable {

        private ReentrantLock lock;
        private LinkedList<Ticket> container;
        private int count;

        public producer(ReentrantLock lock, LinkedList<Ticket> container, int count) {
            this.lock = lock;
            this.container = container;
            this.count = count;
        }

        @Override
        public void run() {

            Random random = new Random();
            while (true) {
                if (container.size() >= count) {
                    System.out.println("票仓满了[" + count + "]");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                } else {
                    lock.lock();
                    int id = random.nextInt(100);
                    container.add(new Ticket(id));
                    System.out.println("生成了一张票ID: " + id);
                    lock.unlock();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class Consumer implements Runnable {
        private ReentrantLock lock;
        private LinkedList<Ticket> container;

        public Consumer(ReentrantLock lock, LinkedList<Ticket> container) {
            this.lock = lock;
            this.container = container;
        }

        @Override
        public void run() {
            while (true) {
                if (container.size() == 0) {
                    System.out.println("票仓空了...");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                } else {
                    lock.lock();
                    Ticket t = container.poll();
                    System.out.println("取到一张票ID: " + t.id);
                    lock.unlock();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        LinkedList<Ticket> container = new LinkedList<>();

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(new producer(lock, container, 10));
        executor.submit(new Consumer(lock, container));

        try {
            executor.awaitTermination(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();

        /*
        new Thread(new producer(lock, container, 10)).start();
        new Thread(new Consumer(lock, container)).start();
        */

    }




}
