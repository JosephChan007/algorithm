package org.joseph.algorithm.lock;

public class ReadWriteLock {

    /**
     * 读锁持有个数
     */
    private int readCount = 0;

    /**
     * 写锁持有个数
     */
    private int writeCount = 0;


    /**
     * 获取读锁,读锁在写锁不存在的时候才能获取
     */
    public synchronized void lockRead() throws InterruptedException {
        while (writeCount > 0) {
            wait();
        }
        readCount++;
    }

    /**
     * 释放读锁
     */
    public synchronized void unLockRead() {
        readCount--;
        notifyAll();
    }

    /**
     * 获取写锁,当读锁存在时需要wait.
     */
    public synchronized void lockWrite() throws InterruptedException {
        if (writeCount > 0) {
            wait();
        }

        writeCount++;

        while (readCount > 0) {
            wait();
        }
    }

    /**
     * 释放读锁
     */
    public synchronized void unLockWrite() {
        writeCount--;
        notifyAll();
    }


}
