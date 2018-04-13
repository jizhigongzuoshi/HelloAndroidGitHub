package cn.com.focus.helloandroidgithub.thread;

import org.junit.Test;

/**
 * Created by MrLu on 2018/3/27.
 */

public class Test1 {
    @Test
    public void test1() throws InterruptedException {
        Thread a = null, b;
         a = new MyThread();
         b = new MyThread((MyThread) a);

        b.start();
        a.start();

         Thread.sleep(1);

    }

    class MyThread extends Thread{
        private int a = -100;
        private MyThread thread;
        MyThread(){

        }
        MyThread(MyThread thread){
            this.thread = thread;
        }
        public int getobject(){
            System.out.println(Thread.currentThread().getName() + "  id " +Thread.currentThread().getId());
            return a;
        }
        @Override
        public void run() {
            while (true) {
                a++;
                System.out.println("main " +Thread.currentThread().getId());
                if (thread != null) {
                    System.out.println(" a = " +thread.getobject());
                }
            }
        }
    }
}
