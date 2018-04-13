package cn.com.focus.helloandroidgithub.hw.camera;/*
package cn.com.focus.testproject.camera;

import android.annotation.TargetApi;
import android.app.Activity;
import android.media.Image;
import android.media.ImageReader;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import cn.com.focus.testproject.R;


*/

import android.annotation.TargetApi;
import android.app.Activity;
import android.media.Image;
import android.media.ImageReader;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import cn.com.focus.helloandroidgithub.R;


/**
 * 作者：卢龙飞 on 2017/2/8 13:25
 */


@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class TwoCameraWithCamer2 extends Activity implements ImageReader.OnImageAvailableListener{
    private Camera2PreviewView camera2Preview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera2);

        camera2Preview = new Camera2PreviewView(this);
        ((LinearLayout)findViewById(R.id.ll_content)).addView(camera2Preview);
    }

    @Override
    public void onImageAvailable(ImageReader reader) {
        Log.e("onImageAvailable", "onImageAvailable() called with: reader = [" + reader + "]");
        Image image = reader.acquireNextImage();
        image.close();
    }
}

