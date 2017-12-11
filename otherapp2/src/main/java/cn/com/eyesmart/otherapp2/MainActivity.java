package cn.com.eyesmart.otherapp2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e(TAG, "onCreate: taskid = "  + getTaskId() );
        Log.e(TAG, "onCreate: taskid = "  + getTaskId()  + "   "+getApplicationInfo());
        openOtherAppActivity();
    }

    public void openOtherAppActivity(){
        String packageName = "cn.com.focus.helloandroidgithub";
        String className = "cn.com.focus.helloandroidgithub.activity.launchmode.singletop.SingleTopActivity";

        Intent intent = new Intent();
        intent.setClassName(packageName, className);
        startActivity(intent);
    }
}
