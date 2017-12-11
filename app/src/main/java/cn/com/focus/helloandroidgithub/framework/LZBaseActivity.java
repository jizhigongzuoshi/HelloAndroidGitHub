package cn.com.focus.helloandroidgithub.framework;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by MrLu on 2017/10/14.
 */


public abstract class LZBaseActivity extends AppCompatActivity {
    public final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate() called with: savedInstanceState = [" + savedInstanceState + "]");
        Log.d(TAG, "taskid = " + getTaskId());
        super.onCreate(savedInstanceState);
        setContentView(layoutResID());
        initView();
        initData();
    }

    @Override
    public void onAttachedToWindow() {
        Log.d(TAG, "onAttachedToWindow() called");
        super.onAttachedToWindow();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.d(TAG, "onNewIntent() called with: intent = [" + intent + "]");
        super.onNewIntent(intent);
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart() called");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        Log.d(TAG, "onRestart() called");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume() called");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause() called");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop() called");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy() called");
        super.onDestroy();
    }

    public <T> T $Id (T t, int id){
        return (T)findViewById(id);
    }
    public abstract void initView();
    public abstract void initData();
    public abstract int layoutResID();
}
