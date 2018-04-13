package cn.com.focus.helloandroidgithub.service;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import cn.com.focus.helloandroidgithub.framework.LZBaseService;

/**
 * Created by MrLu on 2017/11/28.
 */

public class LifeCircleService extends LZBaseService {
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            System.out.println("=========================================");
            mHandler.sendMessageDelayed(Message.obtain(mHandler, 1), 3000);
        }
    };

    private Thread thread = new Thread() {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " =========8888");
            }
        }
    };


    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate() called");
        mHandler.sendMessageDelayed(Message.obtain(mHandler, 1), 3000);
        thread.start();
        super.onCreate();
    }
}
