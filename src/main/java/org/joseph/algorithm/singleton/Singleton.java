package org.joseph.algorithm.singleton;

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
     * volatile作用：避免指令重排。
     * 重排指令为：1、对象进行初始化；2、将初始化完成的对象赋值给instance。避免这两个指令顺序颠倒，造成instance得到一个未初始化完成的对象。
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
