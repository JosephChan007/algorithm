package org.joseph.algorithm.delay;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DelayMessageMain {

    public static void main(String[] args) {
        DelayQueue<DelayMessage> query = new DelayQueue<>();
        int messageCount = 5;
        long delayTime = 2000;

        DelayQueryProducer producer = new DelayQueryProducer(query, messageCount, delayTime);
        DelayQueryConsumer consumer = new DelayQueryConsumer(query, messageCount);

        /*  直接开线程
        new Thread(producer).start();
        new Thread(consumer).start();
        */


        /**
         * 线程池方式
         */
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(producer);
        executor.submit(consumer);

        try {
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();

    }

}
