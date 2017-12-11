package cn.com.focus.helloandroidgithub.utils;

/**
 * Created by MrLu on 2017/12/1.
 */

public class TimerUtils {
    public static void sleep(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
