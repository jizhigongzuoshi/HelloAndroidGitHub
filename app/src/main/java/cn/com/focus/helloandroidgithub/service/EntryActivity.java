package cn.com.focus.helloandroidgithub.service;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cn.com.focus.helloandroidgithub.R;

public class EntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
    }

    public void openServiceAct(View view) {
        Intent intent = new Intent(this, ServiceMainActivity.class);
        startActivity(intent);
    }
}
