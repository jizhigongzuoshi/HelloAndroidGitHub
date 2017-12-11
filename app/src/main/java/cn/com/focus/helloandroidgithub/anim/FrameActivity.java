package cn.com.focus.helloandroidgithub.anim;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import cn.com.focus.helloandroidgithub.R;

/**
 * Created by MrLu on 2017/11/24.
 */

public class FrameActivity extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frameanima);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        ImageView imageView = (ImageView) findViewById(R.id.iv);
        /*AnimationDrawable drawable = (AnimationDrawable) imageView.getDrawable();
        drawable.setOneShot(false);*/
        //drawable.start();

        AnimationSet anim = (AnimationSet) AnimationUtils.loadAnimation(this, R.anim.complex);
        anim.setRepeatCount(10);
        anim.setRepeatMode(Animation.REVERSE);
        imageView.setAnimation(anim);
        anim.start();


    }
}
