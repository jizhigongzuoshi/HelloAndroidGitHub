package cn.com.eyesmart.otherapp;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e(TAG, "onCreate: taskid = "  + getTaskId() );
        //openOtherAppActivity();
        System.out.println("ActivityManager ==========" + android.os.Process.myPid() +  "  " +
                android.os.Process.myTid()  + "  " );
        bindService();
    }

    public void openOtherAppActivity(){
        String packageName = "cn.com.focus.helloandroidgithub";
        String className = "cn.com.focus.helloandroidgithub.activity.launchmode.singletop.SingleTopActivity";

        Intent intent = new Intent();
        intent.setClassName(packageName, className);
        System.out.println("ActivityManager ==========" + Thread.currentThread().getId());
        startActivity(intent);
    }

    public void bindService(){
        Intent intent = new Intent();
        intent.setClassName("cn.com.focus.helloandroidgithub", "cn.com.focus.helloandroidgithub.service.IPCService");
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Toast.makeText(MainActivity.this, "bindsucess " + Thread.currentThread().getId(), 0).show();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, BIND_AUTO_CREATE);
    }
}
