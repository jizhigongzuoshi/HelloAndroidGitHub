package cn.com.focus.helloandroidgithub.activity.customview;

import android.view.MotionEvent;
import android.widget.Button;
import android.widget.LinearLayout;

import cn.com.focus.helloandroidgithub.R;
import cn.com.focus.helloandroidgithub.framework.LZBaseActivity;

/**
 * Created by MrLu on 2018/1/22.
 */

public class MainActivity extends LZBaseActivity {
    private LinearLayout linearLayout;
    //private FrameLayout fr_conten;
    private Button btn;
    @Override
    public void initView() {
        linearLayout = (LinearLayout) findViewById(R.id.ll_content);
        btn = (Button) findViewById(R.id.btn);
    }

    @Override
    public void initData() {
        linearLayout.post(new Runnable() {
            @Override
            public void run() {
//                Toast.makeText(MainActivity.this, "W: " + fr_conten.getMeasuredWidth()+
//                        "H: " + fr_conten.getMeasuredHeight(), 0).show();
            }
        });
    }

    @Override
    public int layoutResID() {
        return R.layout.activity_customview;
    }
    private int ly = 0;
    float oldY = 0;
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        super.dispatchTouchEvent(ev);
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                oldY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float y = ev.getY();
                if (y > oldY){
                    ly -= 10;
                }else {
                    ly += 10;
                }
                oldY = y;
                //linearLayout.scrollTo(0, (int) ev.getY());
                linearLayout.scrollTo(0, ly);
                break;
        }

        return true;
    }
}
