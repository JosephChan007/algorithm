package org.joseph.algorithm.delay;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

public class DelayQueryProducer implements Runnable {

    private DelayQueue<DelayMessage> queue;
    private int messageCount;
    private long delayTime;

    public DelayQueryProducer(DelayQueue<DelayMessage> queue, int messageCount, long delayTime) {
        this.queue = queue;
        this.messageCount = messageCount;
        this.delayTime = delayTime;
    }

    public DelayQueue<DelayMessage> getQueue() {
        return queue;
    }

    public void setQueue(DelayQueue<DelayMessage> queue) {
        this.queue = queue;
    }

    public int getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(int messageCount) {
        this.messageCount = messageCount;
    }

    public long getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(long delayTime) {
        this.delayTime = delayTime;
    }

    @Override
    public void run() {
        Random r = new Random();
        for (int i = 0; i < messageCount; i++) {
            int messageId = r.nextInt(99999);
            DelayMessage message = new DelayMessage(messageId, delayTime);
            this.getQueue().add(message);
            System.out.println("produce message id is[" + new Date() + "]: " + messageId);
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
