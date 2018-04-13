package cn.com.focus.helloandroidgithub.activity.canvas;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;

import cn.com.focus.helloandroidgithub.R;

/**
 * Created by MrLu on 2018/1/18.
 */

public class Layers extends Activity{
    private Bitmap oy;
    private Bitmap oz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new SampleView(this));
    }
    private static class SampleView extends View {
        private static final int LAYER_FLAGS = Canvas.MATRIX_SAVE_FLAG | Canvas.CLIP_SAVE_FLAG
                | Canvas.HAS_ALPHA_LAYER_SAVE_FLAG | Canvas.FULL_COLOR_LAYER_SAVE_FLAG
                | Canvas.CLIP_TO_LAYER_SAVE_FLAG;
        private final Bitmap oy;
        private final Bitmap oz;
        private Paint mPaint;
        public SampleView(Context context) {
            super(context);
            setFocusable(true);
            mPaint = new Paint();
            mPaint.setAntiAlias(true);

            Bitmap oysrc = BitmapFactory.decodeResource(getResources(), R.drawable.oy).copy(Bitmap.Config.ARGB_8888, true);
            oz= BitmapFactory.decodeResource(getResources(), R.drawable.oz);
            Matrix matrix = new Matrix();
            matrix.postScale(-2, -1);
            oy = Bitmap.createBitmap(oysrc, 0, 0, oysrc.getWidth(), oysrc.getHeight(), matrix, true);
            //oy =  Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888);


            Canvas canvas = new Canvas(oy);
            canvas.drawColor(Color.YELLOW, PorterDuff.Mode.DST_OVER);
            canvas.drawBitmap(oz,0,0,mPaint);


        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            //setMeasuredDimension(200, 200);
        }

        @SuppressLint("WrongConstant")
        @Override
        protected void onDraw(Canvas canvas) {
            /*canvas.drawColor(Color.WHITE);
            canvas.translate(10, 10);
            mPaint.setColor(Color.RED);
            canvas.drawCircle(75, 75, 75, mPaint);
            canvas.saveLayerAlpha(0, 0, 200, 200, 0x88, LAYER_FLAGS);
            mPaint.setColor(Color.BLUE);
            canvas.drawCircle(125, 125, 75, mPaint);
            canvas.restore();*/

            canvas.drawBitmap(oy, 0, 0, mPaint);
            setBackgroundColor(Color.RED);
        }
    }
}