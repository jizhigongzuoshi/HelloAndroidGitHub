package cn.com.focus.helloandroidgithub.eventdispatch;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import cn.com.focus.helloandroidgithub.R;

/**
 * Created by MrLu on 2017/12/27.
 */

public class MainActivity extends Activity{
    private MyButton myButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventdispatch);
        myButton = (MyButton) findViewById(R.id.btn);

        myButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(MainActivity.this, "OnTouchListener", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }


    public void clickme(View view) {
        Toast.makeText(this, "clickme", Toast.LENGTH_SHORT).show();
    }
}
