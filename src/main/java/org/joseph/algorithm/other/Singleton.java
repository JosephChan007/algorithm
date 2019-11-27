package org.joseph.algorithm.other;

/**
 * 单例：两种安全的实现方式
 */
public class Singleton {

    private static volatile Singleton instance = null;

    private Singleton() {
    }

    private static class SingletonHolder {
        private final static Singleton instance = new Singleton();
    }

    /**
     * 静态内部类实现方式
     */
    public static Singleton getInstance() {
        return SingletonHolder.instance;
    }

    /**
     * 同步+Double-check实现方式
     */
    public static Singleton getInstance1() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
