package cn.com.focus.helloandroidgithub.hw.camera;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.ImageReader;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.util.Size;
import android.view.Surface;
import android.view.TextureView;

import java.util.Arrays;


@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class Camera2PreviewView extends TextureView implements TextureView.SurfaceTextureListener {
    private String TAG = "Camera2PreviewView";
    private Handler cameraHandler;
    private HandlerThread mHandlerThread;
    private Size mPreviewSize;
    private CameraDevice mCamera;
    private String mCameraId;
    private ImageReader mImageReader;
    private CaptureRequest.Builder mPreviewBuilder;
    private CameraCaptureSession mSession;
    private ImageReader.OnImageAvailableListener mOnImageAvailableListener;
    private boolean isDestroy = false;

    // --------------第一次回调，打开相机后回调，可执行Camera.createCaptureSession()------------------------
    private CameraDevice.StateCallback mCameraDeviceStateCallback = new CameraDevice.StateCallback() {
        @Override
        public void onOpened(CameraDevice camera) {
            Log.e(TAG, "CameraDevice-----onOpened()");
            try {
                mCamera = camera;
                startPreview(camera);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onDisconnected(CameraDevice camera) {
            Log.e(TAG, "CameraDevice-----onDisconnected()");
            destroy();
        }

        //上次camera没有完全释放掉，就开启了新的camera，就会回调此方法
        @Override
        public void onError(CameraDevice camera, int error) {

//            camera.close();
//            mCamera = null;
        }
    };

    // --------------第二次回调，创建Session后回调，可执行CameraCaptureSession.setRepeatingRequest()------------------------
    private CameraCaptureSession.StateCallback mSessionStateCallback = new CameraCaptureSession.StateCallback() {
        @Override
        public void onConfigured(CameraCaptureSession session) {
            Log.e(TAG, "CameraCaptureSession-----onConfigured()");
            mSession = session;

            // 开启红外灯光
            if (Build.MODEL.equals("jiaotu_tc")) {
            }
            try {
                // 开启预览
                session.setRepeatingRequest(mPreviewBuilder.build(), null, null);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onConfigureFailed(CameraCaptureSession session) {
            Log.e(TAG, "CameraCaptureSession-----onConfigureFailed()");
        }
    };
    private long start_time;

    public Camera2PreviewView(Context context) {
        super(context);
        mOnImageAvailableListener = (ImageReader.OnImageAvailableListener) context;

        mHandlerThread = new HandlerThread("CAMERA2");
        mHandlerThread.start();
        cameraHandler = new Handler(mHandlerThread.getLooper());
        setSurfaceTextureListener(this);
    }

    private CameraManager mCameraManager;

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        try {
            mCameraManager = (CameraManager) getContext().getSystemService(Context.CAMERA_SERVICE);
            //打开虹膜摄像头
            openIrisCamera(mCameraManager);
            // 获得属性CameraCharacteristics：CameraDevice的属性描述类,描述相机硬件设备支持可用的和输出的参数
            CameraCharacteristics characteristics = mCameraManager.getCameraCharacteristics(mCameraId);
            // 支持的STREAM CONFIGURATION
            StreamConfigurationMap map = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
            Size[] outputSizes = map.getOutputSizes(SurfaceTexture.class);
            // 设置控件旋转
            int rotation = characteristics.get(CameraCharacteristics.SENSOR_ORIENTATION);
            Log.e(TAG, "SENSOR_ORIENTATION:" + rotation);

            // 获取最大的预览尺寸
            mPreviewSize = getMaxSize(outputSizes);
            if (Build.MODEL.equals("jiaotu_tc")) {
                mPreviewSize = outputSizes[1];
            }
            //把预览图像的宽高记录下来
            // 打开相机
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                //Toast.makeText(getContext(), R.string.toast_permission, Toast.LENGTH_SHORT).show();
                return;
            }

            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            mCameraManager.openCamera(mCameraId, mCameraDeviceStateCallback, null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        destroy();
        surface.release();
        return true;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {
    }

    // -------------------------开始预览（第一次回调）------------------------------
    private void startPreview(CameraDevice camera) throws CameraAccessException {
        SurfaceTexture texture = getSurfaceTexture();
        // 设置适当的大小和格式去匹配相机设备的可支持的大小和格式
        texture.setDefaultBufferSize(mPreviewSize.getWidth(), mPreviewSize.getHeight());
        Log.e(TAG, "mPreviewSize:" + mPreviewSize.getWidth() + "*" + mPreviewSize.getHeight());
        Surface surface = new Surface(texture);
        try {
            mPreviewBuilder = camera.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

        // 创建一个ImageReader对象，用于获取摄像头的图像数据
        mImageReader = ImageReader.newInstance(mPreviewSize.getWidth(), mPreviewSize.getHeight(), ImageFormat.YUV_420_888, 2);
        mImageReader.setOnImageAvailableListener(mOnImageAvailableListener, cameraHandler);

        // 设置数据传输目标
//        mPreviewBuilder.addTarget(surface);
        mPreviewBuilder.addTarget(mImageReader.getSurface());

        // 通过mPreviewBuilder.set(key, value)方法，设置拍照参数
        // 设置预览为黑白效果
        mPreviewBuilder.set(CaptureRequest.CONTROL_EFFECT_MODE, CaptureRequest.CONTROL_EFFECT_MODE_MONO);
        mPreviewBuilder.set(CaptureRequest.CONTROL_AE_MODE, CaptureRequest.CONTROL_AE_MODE_ON);
        // JPEG方向设置，
        // int rotation =
        // this.getWindowManager().getDefaultDisplay().getRotation();
        // mPreviewBuilder.set(CaptureRequest.JPEG_ORIENTATION,
        // ORIENTATIONS.get(rotation));

        // 创建由相机设备的输出surface组成的拍照会话
        camera.createCaptureSession(Arrays.asList(mImageReader.getSurface()), mSessionStateCallback, null);
    }


    public void destroy() {
        isDestroy = true;
        Log.d(TAG, "------------ destroy() is called!");
        if (Build.MODEL.equals("jiaotu_tc")) {
        }
        if (Build.MODEL.equals("E601")) {
        }
        if (mPreviewBuilder != null && mImageReader != null) {
            mPreviewBuilder.removeTarget(mImageReader.getSurface());
            mPreviewBuilder = null;
        }
        if (mSession != null) {
            mSession.close();
            mSession = null;
        }
        if (mHandlerThread != null) {
            mHandlerThread.quit();
        }
        new Thread(new Runnable() {

            @Override
            public void run() {
                if (mCamera != null) {
                    mCamera.close();
                    Log.d(TAG, "------------ mCamera is close!");
                    mCamera = null;
                }
            }
        }).start();

        if (mImageReader != null) {
            mImageReader.close();
            mImageReader = null;
        }
        if (cameraHandler != null) {
            cameraHandler.removeCallbacksAndMessages(null);
            cameraHandler = null;
        }

    }

    public boolean isDestroy() {
        return isDestroy;
    }

    // ------------------------打开虹膜摄像头--------------------
    private void openIrisCamera(CameraManager cameraManager) {
        String[] cameraIdList = null;
        try {
            cameraIdList = cameraManager.getCameraIdList();
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

        if (Build.MODEL.equals("Coolpad Y90")
                || Build.MODEL.equals("Coolpad T2-C01")) {
            mCameraId = "0"; // 打开唯一的摄像头
        } else if (Build.MODEL.equals("6045Y")
                || Build.MODEL.equals("Full AOSP on Sofia")
                || Build.MODEL.equals("LPM5502")) {
            mCameraId = "1";
        } else if (cameraIdList.length > 0) {
            mCameraId = cameraIdList.length - 1 + "";//打开ID最后的摄像头
        } else {
        }
    }

    // -----------------------获取最大的预览尺寸--------------------
    private Size getMaxSize(Size[] outputSizes) {
        Size sizeMax = null;
        if (outputSizes != null) {
            sizeMax = outputSizes[0];
            for (Size size : outputSizes) {
                Log.e(TAG, "------- size.getWidth()===" + size.getWidth() + ",size.getHeight()===" + size.getHeight());
                if (size.getWidth() * size.getHeight() > sizeMax.getWidth() * sizeMax.getHeight()) {
                    sizeMax = size;
                }
            }
        }
        Log.e(TAG, "------- sizeMax.getWidth()===" + sizeMax.getWidth() + ",sizeMax.getHeight()===" + sizeMax.getHeight());
        return sizeMax;
    }

    public interface Camera2MonitorListener {
        public void camera2OpenSuccess();

        public void camera2OpenFail();
    }

}
