package cn.com.focus.helloandroidgithub.hw.camera;

import android.content.Intent;
import android.hardware.Camera;
import android.view.TextureView;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

import cn.com.eyesmart.eyematrix.jniInterface.EyeMatrix;
import cn.com.focus.helloandroidgithub.R;
import cn.com.focus.helloandroidgithub.activity.killotherprocess.MainActivity;
import cn.com.focus.helloandroidgithub.framework.LZBaseActivity;
import cn.com.focus.helloandroidgithub.service.LifeCircleService;

/**
 * Created by MrLu on 2017/12/14.
 */

public class CameraBaseActivity extends LZBaseActivity implements Camera.PreviewCallback {
    private TextureView textureView;
    private Camera mCamera;
    private boolean ismSave;
    /*TextureView.SurfaceTextureListener mSurfaceTextureListener = new TextureView.SurfaceTextureListener() {
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
            if (mCamera != null) {
                mCamera.setPreviewCallback(null);
                mCamera.stopPreview();
                mCamera.release();
                surface.release();
                mCamera = null;
            }
            return false;
        }

        @Override
        public void onSurfaceTextureUpdated(SurfaceTexture surface) {

        }
    };
*/
    @Override
    public void initView() {
        textureView = (TextureView) findViewById(R.id.textureview);
        //textureView.setSurfaceTextureListener(mSurfaceTextureListener);
        textureView.setScaleX(-1);
        startService(new Intent(this, LifeCircleService.class));
    }

    @Override
    public void initData() {

    }

    @Override
    public int layoutResID() {
        return R.layout.activity_camera_base;
    }

    private void openCamera() {
        mCamera = Camera.open(0);
        try {
            mCamera.setPreviewTexture(textureView.getSurfaceTexture());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Camera.Parameters parameters = mCamera.getParameters();
        mCamera.setPreviewCallback(this);
        parameters.setPreviewSize(1920, 1080);
        mCamera.setParameters(parameters);
        mCamera.startPreview();
    }

    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {
        if (ismSave) {
            String fileName = "/mnt/sdcard/Eyesmart/ " + System.currentTimeMillis() +".bmp";
            EyeMatrix.SaveGrayToBMP(data, 1920, 1080, fileName);
            Toast.makeText(this, fileName, Toast.LENGTH_SHORT).show();
        }

        ismSave = false;
    }

    public void savePreviewImg(View view) throws InterruptedException {
        ismSave = true;
        Thread.sleep(15000);
        startActivity(new Intent(this, MainActivity.class));
    }
}
