package org.joseph.algorithm.thread;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumer {

    private ReentrantLock lock = new ReentrantLock();
    private LinkedList<Integer> queue = new LinkedList<>();
    private static int threshold = 100;

    public Integer get() {
        lock.lock();
        try {
            if(queue.size() == 0) return -1;
            return queue.poll();
        } finally {
            lock.unlock();
        }
    }

    public void put(Integer val) {
        lock.lock();
        try {
            if (queue.size() == threshold) return;
            queue.add(val);
        } finally {
            lock.unlock();
        }
    }

    public LinkedList<Integer> getQueue() {
        return queue;
    }

    public static void main(String[] args) {

        ProducerConsumer instance = new ProducerConsumer();
        Random random = new Random();

        new Thread(() -> {
            while (true) {
                int ran2 = random.nextInt(100);
                instance.put(ran2);
                System.out.println("input: " + ran2);
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                Integer value = instance.get();
                System.out.println("output: " + value);
                try {
                    TimeUnit.MILLISECONDS.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                System.out.println("size: " + instance.getQueue().size());
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
