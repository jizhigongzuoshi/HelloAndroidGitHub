package cn.com.focus.helloandroidgithub.activity.killotherprocess;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import cn.com.focus.helloandroidgithub.R;
import cn.com.focus.helloandroidgithub.framework.LZBaseActivity;
import cn.com.focus.helloandroidgithub.hw.camera.CameraBaseActivity;

/**
 * Created by MrLu on 2018/1/26.
 */

public class MainActivity extends LZBaseActivity{
    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public int layoutResID() {
        return R.layout.activity_killbackgroud;
    }

    public void kill(View view) throws InterruptedException {
        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        am.killBackgroundProcesses("cn.com.focus.helloandroidgithub");


        Intent mStartActivity = new Intent(this, CameraBaseActivity.class);
        int mPendingIntentId = 123456;
        PendingIntent mPendingIntent = PendingIntent.getActivity(this, mPendingIntentId,    mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager mgr = (AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 8000, mPendingIntent);
//        System.exit(0);
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
