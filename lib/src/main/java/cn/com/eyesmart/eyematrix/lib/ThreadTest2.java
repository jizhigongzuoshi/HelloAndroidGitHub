package cn.com.eyesmart.eyematrix.lib;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by MrLu on 2018/3/29.
 */

public class ThreadTest2 {
    public static void main(String[] args) {
        final Business business = new Business();
        new Thread(new Runnable() {
            @Override
            public void run() {
                threadExecute(business, "sub");
            }
        }).start();
        threadExecute(business, "main");
    }

    public static void threadExecute(Business business, String threadType) {
        for (int i = 0; i < 100; i++) {
            try {
                if ("main".equals(threadType)) {
                    business.main(i);
                } else {
                    business.sub(i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Business {
    private boolean bool = true;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public /*synchronized*/ void main(int loop) throws InterruptedException {
        lock.lock();
        System.out.println("main  lock ********************************************* ");
        try {
            while (bool) {
                System.out.println("main before await ********************************************* ");
                condition.await();//this.wait();
                System.out.println("main after await ********************************************* ");
            }
            for (int i = 0; i < 100; i++) {
                System.out.println("main thread seq of " + i + ", loop of " + loop);
            }
            bool = true;
            condition.signal();//this.notify();
        } finally {
            lock.unlock();
            System.out.println("main  unlock ********************************************* [" + loop + "]");

        }
    }

    public /*synchronized*/ void sub(int loop) throws InterruptedException {
        lock.lock();
        System.out.println("sub lock ============================================== ");
        try {
            while (!bool) {
                System.out.println("sub before await ============================================== ");
                condition.await();//this.wait();
                System.out.println("sub after await ============================================== ");
            }
            for (int i = 0; i < 10; i++) {
                System.out.println("sub thread seq of " + i + ", loop of " + loop);
            }
            bool = false;
            condition.signal();//this.notify();
        } finally {
            lock.unlock();
            System.out.println("sub unlock ==============================================");

        }
    }
}
