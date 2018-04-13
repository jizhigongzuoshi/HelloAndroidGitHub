package cn.com.focus.helloandroidgithub.view.matrix;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import cn.com.focus.helloandroidgithub.R;
import cn.com.focus.helloandroidgithub.framework.LZBaseActivity;

/**
 * Created by MrLu on 2017/12/13.
 */

public class MainActivity extends LZBaseActivity{
    private ImageView imageView;
    private Button btn_translate;
    private Bitmap bitmap;
    private Matrix currentMatrix;

    @Override
    public void initView() {
        imageView = (ImageView) findViewById(R.id.iv_image);
        btn_translate = (Button) findViewById(R.id.btn_translate);


    }

    @Override
    public void initData() {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cap_00000);
        imageView.setImageBitmap(bitmap);
        currentMatrix = new Matrix();
        btn_translate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                currentMatrix.postTranslate(50, 50);
               /* Bitmap bm = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                        bitmap.getHeight(), matrix, true);*/
                //imageView.setImageBitmap(bm);
                imageView.setImageMatrix(currentMatrix);
            }
        });



    }

    @Override
    public int layoutResID() {
        return R.layout.activity_view_matrix;
    }
}
