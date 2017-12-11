package cn.com.focus.helloandroidgithub.activity.launchmode.singletop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import cn.com.focus.helloandroidgithub.R;
import cn.com.focus.helloandroidgithub.framework.LZBaseActivity;

public class SingleTopActivity extends LZBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public int layoutResID() {
        return R.layout.activity_singletop;
    }

    public void openA(View view) {
        startActivity(new Intent(this, StandardActivityA.class));
    }

    public void openB(View view) {
        startActivity(new Intent(this, StandardActivityB.class));
    }

    public void openSingleTop(View view) {

        startActivity(new Intent(this, SingleTopActivity.class));

    }
}
