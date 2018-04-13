package cn.com.focus.helloandroidgithub.hw.camera;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.TextureView;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import cn.com.focus.helloandroidgithub.R;
import cn.com.focus.helloandroidgithub.framework.LZBaseActivity;

/**
 * Created by MrLu on 2017/12/1.
 */

public class SingleCameraActivity extends LZBaseActivity implements Camera.PreviewCallback {
    private TextureView textureView;
    private ImageView iv_circle;
    Camera mCamera;
    private boolean isPreview = true;
    ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1);

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            newScheduledThreadPool.shutdownNow();
            restartMediaServer();
            new Thread() {
                @Override
                public void run() {
                    //releaseCam();
                    try {
                        Thread.sleep(4000);
                        mCamera.startPreview();
                    } catch (Exception e) {
                        e.printStackTrace();
                        openCamera();
                    }


                }
            }.start();


        }
    };


    TextureView.SurfaceTextureListener mSurfaceTextureListener = new TextureView.SurfaceTextureListener() {
        @Override
        public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
            DisplayMetrics dm = getResources().getDisplayMetrics();
            ViewGroup.LayoutParams layoutParams = textureView.getLayoutParams();
            layoutParams.width = dm.widthPixels;
            layoutParams.height = dm.heightPixels;
            textureView.setLayoutParams(layoutParams);

            Log.e(TAG, "onSurfaceTextureAvailable: screenW " + dm.widthPixels + "  screeH" + dm.heightPixels);
            openCamera();
        }

        @Override
        public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

        }

        @Override
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
            return false;
        }

        @Override
        public void onSurfaceTextureUpdated(SurfaceTexture surface) {

        }
    };

    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            System.out.println("\n\n\n");
            Log.e(TAG, "stoppreview==========");
            handler.sendMessageDelayed(Message.obtain(handler, 2), 4000);
            startPreview(!isPreview);
            handler.removeMessages(2);

            isPreview = !isPreview;
        }
    };

    private void openCamera() {
        try {
            mCamera = Camera.open(2);
            mCamera.setPreviewTexture(textureView.getSurfaceTexture());
        } catch (Exception e) {
            e.printStackTrace();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        Camera.Parameters parameters = mCamera.getParameters();
        mCamera.setPreviewCallback(this);
        mCamera.setParameters(parameters);
        mCamera.startPreview();
        newScheduledThreadPool = Executors.newScheduledThreadPool(1);
        newScheduledThreadPool.scheduleAtFixedRate(timerTask, 2000, 2000, TimeUnit.MILLISECONDS);
    }

    @Override
    public void initView() {
        textureView = (TextureView) findViewById(R.id.textureview);
        textureView.setSurfaceTextureListener(mSurfaceTextureListener);
    }

    @Override
    public void initData() {

    }

    @Override
    public int layoutResID() {
        return R.layout.activity_singlecamera;
    }

    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {

    }

    private void restartMediaServer() {
        Log.e(TAG, "restartMediaServer==========");
        /*Camera open = Camera.open(0);
        Camera.Parameters parameters = open.getParameters();
        parameters.set("RestartMediaServer", 1);
        open.setParameters(parameters);*/
        final Intent intent = new Intent();
        //ComponentName的参数1:目标app的包名,参数2:目标app的Service完整类名
        intent.setComponent(new ComponentName("cn.com.eyesmart.restartcameraservice", "cn.com.eyesmart.restartcameraservice.RestartCameraService"));
        //打开目标AppService
        this.startService(intent);
    }

    private void releaseCam() {
        mCamera.setPreviewCallback(null);
        mCamera.stopPreview();
        mCamera.release();
        mCamera = null;
    }

    private void reOpenCamera() {
        try {
            mCamera = Camera.open(2);
            mCamera.setPreviewTexture(textureView.getSurfaceTexture());
        } catch (Exception e) {
            e.printStackTrace();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        Camera.Parameters parameters = mCamera.getParameters();
        mCamera.setPreviewCallback(this);
        mCamera.setParameters(parameters);
        mCamera.startPreview();
    }

    private void startPreview(boolean isPreview) {
        if (isPreview) {
            mCamera.startPreview();
        } else {
            mCamera.stopPreview();
        }
    }
}
