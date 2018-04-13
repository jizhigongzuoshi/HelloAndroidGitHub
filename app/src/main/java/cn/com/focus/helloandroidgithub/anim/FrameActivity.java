package cn.com.focus.helloandroidgithub.anim;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;

import cn.com.focus.helloandroidgithub.R;

/**
 * Created by MrLu on 2017/11/24.
 */

public class FrameActivity extends Activity{
    ImageView imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frameanima);
       // Arrays.asList( "a", "b", "d" ).forEach(e -> System.out.println( e ));
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        imageView = (ImageView) findViewById(R.id.iv);
        /*AnimationDrawable drawable = (AnimationDrawable) imageView.getDrawable();
        drawable.setOneShot(false);*/
        //drawable.start();

        /*AnimationSet anim = (AnimationSet) AnimationUtils.loadAnimation(this, R.anim.complex);
        anim.setRepeatCount(10);
        anim.setRepeatMode(Animation.REVERSE);
        imageView.setAnimation(anim);
        anim.start();*/

        CustomAnimation customAnimation = new CustomAnimation();
        customAnimation.setDuration(3000);
        imageView.setAnimation(customAnimation);
        customAnimation.start();


    }

    public class CustomAnimation extends Animation{
        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            System.out.println("interpolatedTime = [" + interpolatedTime + "], t = [" + t + "]");
            float sx = 1.0f;
            float sy = 1.0f;
            float scale = getScaleFactor();

            sx += interpolatedTime;
            sy +=interpolatedTime;
            t.getMatrix().setScale(sx, sy, scale * 20, scale * 20);
            t.getMatrix().setRotate(interpolatedTime * 10);

        }
    }
}
