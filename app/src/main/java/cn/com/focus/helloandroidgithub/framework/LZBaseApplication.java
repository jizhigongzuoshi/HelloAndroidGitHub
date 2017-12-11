package cn.com.focus.helloandroidgithub.framework;

import android.app.Application;
import android.util.Log;

import static cn.com.focus.helloandroidgithub.framework.Constant.TAG;


/**
 * Created by MrLu on 2017/10/18.
 */

public class LZBaseApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate() called");
    }
}
