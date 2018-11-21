package com.kevin.algorithm.algosummary.algoDemo;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName: SingleTone
 * @Description: 单例模式的多种实现方法
 * @Author: Kevin
 * @Date: 2018/10/22 09:55
 */
public class SingleTone {

    private static SingleTone instance0 = new SingleTone();
    private static volatile SingleTone instance = null;

    private static final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private SingleTone() {
    }

    public static SingleTone getInstance0() {
        return instance0;
    }

    public static synchronized SingleTone getInstance1() {
        if (null == instance) {
            instance = new SingleTone();
        }
        return instance;
    }

    public static SingleTone getInstance2() {
        if (null == instance) {
            synchronized (SingleTone.class) {
                if (null == instance) {
                    /**
                     * instance = new SingleTone() 其实由两条语句构成， 一个是new ，一个是赋值， 有可能发生有其他线程判断instance==null时，会发现为false,不为null, 但是当调用的时候，却没有找到对应的对象。
                     * 这里是因为发生了指令重排，需要使用volatile来禁止指令重排。
                     */
                    instance = new SingleTone();
                }
            }
        }
        return instance;
    }

    public static SingleTone getInstance3() {
        readWriteLock.readLock().lock();
        try {
            if (null == instance) {
                readWriteLock.readLock().unlock();
                readWriteLock.writeLock().lock();
                try {
                    if (null == instance) {
                        instance = new SingleTone();
                    }
                } finally {
                    readWriteLock.writeLock().unlock();
                }
                readWriteLock.readLock().lock();
            }
        } finally {
            readWriteLock.readLock().unlock();
        }
        return instance;
    }

    public static SingleTone getInstance4() {
        readWriteLock.readLock().lock();
        try {
            if (null != instance) {
                return instance;
            }
        } finally {
            readWriteLock.readLock().unlock();
        }
        readWriteLock.writeLock().lock();
        try {
            if (null == instance) {
                instance = new SingleTone();
            }
        } finally {
            readWriteLock.writeLock().unlock();
        }
        return instance;
    }

    private static class SingletonHolder {

        static final SingleTone SINGLE_TONE = new SingleTone();
    }

    public static SingleTone getInstance() {
        return SingletonHolder.SINGLE_TONE;
    }

}
