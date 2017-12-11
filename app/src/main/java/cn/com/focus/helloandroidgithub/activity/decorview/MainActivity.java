package cn.com.focus.helloandroidgithub.activity.decorview;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.com.focus.helloandroidgithub.R;

/**
 * Created by MrLu on 2017/12/11.
 */

public class MainActivity extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.service_activity_main);
        FrameLayout frcontent = (FrameLayout)getWindow().getDecorView().findViewById(android.R.id.content);
        frcontent.setClickable(true);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        linearLayout.setBackgroundColor(Color.RED);
        //屏蔽下层的点击事件
        linearLayout.setClickable(true);
        TextView textView = new TextView(this);
        textView.setWidth(200);
        textView.setHeight(200);
        textView.setText("hello world");
        linearLayout.addView(textView);
        frcontent.addView(linearLayout);
    }
}
