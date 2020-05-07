package org.joseph.algorithm.delay;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayMessage implements Delayed {

    private int messageId;
    private long delayTime;

    public DelayMessage(int messageId, long delayTime) {
        this.messageId = messageId;
        this.delayTime = delayTime + System.currentTimeMillis();
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public long getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(long delayTime) {
        this.delayTime = delayTime;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long diffTime = this.getDelayTime() - System.currentTimeMillis();
        return unit.convert(diffTime, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return (int) (this.getDelayTime() - ((DelayMessage) o).getDelayTime());
    }

}
