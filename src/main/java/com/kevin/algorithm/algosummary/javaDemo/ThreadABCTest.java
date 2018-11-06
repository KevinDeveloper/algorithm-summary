package com.kevin.algorithm.algosummary.javaDemo;

import java.util.concurrent.Semaphore;

/**
 * @ClassName: ThreadABCTest
 * @Description:
 * @Author: Kevin
 * @Date: 2018/11/5 19:08
 */
public class ThreadABCTest {

    class TreadABC extends Thread {
        Semaphore lastSemaphore;
        Semaphore curSemaphore;
        String printChar;

        public TreadABC(Semaphore lastSemaphore, Semaphore curSemaphore, String printChar) {
            this.lastSemaphore = lastSemaphore;
            this.curSemaphore = curSemaphore;
            this.printChar = printChar;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    lastSemaphore.acquire();
                    System.out.print(printChar);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    curSemaphore.release();
                }
            }


        }
    }

    public void test(){
        Semaphore sa = new Semaphore(0);
        Semaphore sb = new Semaphore(0);
        Semaphore sc = new Semaphore(1);

        TreadABC a = new TreadABC(sc, sa, "A");
        TreadABC b = new TreadABC(sa, sb, "B");
        TreadABC c = new TreadABC(sb, sc, "C");
        a.start();
        b.start();
        c.start();
    }

    public static void main(String[] args) {
        ThreadABCTest test = new ThreadABCTest();
        test.test();
    }


}
