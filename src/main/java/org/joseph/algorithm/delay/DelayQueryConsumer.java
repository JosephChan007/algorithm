package org.joseph.algorithm.delay;

import java.util.Date;
import java.util.concurrent.DelayQueue;

public class DelayQueryConsumer implements Runnable {

    private DelayQueue<DelayMessage> queue;
    private int messageCount;

    public DelayQueryConsumer(DelayQueue<DelayMessage> queue, int messageCount) {
        this.queue = queue;
        this.messageCount = messageCount;
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

    @Override
    public void run() {
        for (int i = 0; i < messageCount; i++) {
            try {
                DelayMessage message = this.getQueue().take();
                System.out.println("take message id is[" + new Date() + "]: " + message.getMessageId());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
