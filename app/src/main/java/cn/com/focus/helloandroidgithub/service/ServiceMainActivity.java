package cn.com.focus.helloandroidgithub.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cn.com.focus.helloandroidgithub.R;

public class ServiceMainActivity extends AppCompatActivity {
    ServiceConnection connection = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_activity_main);
    }

    public void start_service(View view) {
        Intent intent = new Intent(this, LifeCircleService.class);
        startService(intent);
    }

    public void bind_service(View view) {
        Intent intent = new Intent(this, LifeCircleService.class);
        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        bindService(intent, connection, BIND_AUTO_CREATE);
    }

    public void unbind_service(View view) {
        Intent intent = new Intent(this, LifeCircleService.class);
        unbindService(connection);
        connection = null;
    }

    public void stop_service(View view) {
        Intent intent = new Intent(this, LifeCircleService.class);
        stopService(intent);
    }
}
