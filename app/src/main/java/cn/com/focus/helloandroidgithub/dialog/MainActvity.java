package cn.com.focus.helloandroidgithub.dialog;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.WindowManager;

import cn.com.focus.helloandroidgithub.R;
import cn.com.focus.helloandroidgithub.framework.LZBaseActivity;

/**
 * Created by MrLu on 2017/10/16.
 */

public class MainActvity extends LZBaseActivity {

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public int layoutResID() {
        return R.layout.activity_dialogentry;
    }

    public void show(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(getApplicationContext())
                .setTitle("标题")
                .setMessage("内容")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
               .create();
        alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        alertDialog.show();

    }
}
