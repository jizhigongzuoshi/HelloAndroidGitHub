package cn.com.focus.helloandroidgithub;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.File;

/**
 * Created by MrLu on 2018/2/22.
 */

public class TestActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button button = new Button(this);
        ViewGroup.LayoutParams layoutParams = button.getLayoutParams();
        button.setBackground(new ColorDrawable(Color.RED));
        button.setText("start");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.fromFile(new File("/storage/sdcard0/1325376090171.png"));
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(uri, "image/png");


                startActivity(intent);
            }
        });

        setContentView(button, new ViewGroup.LayoutParams(300, 300));

        Intent intent1 = getIntent();
        if (intent1 != null) {
            //System.out.println(intent1.getData().getPath());
            Uri data = intent1.getData();
            if (data != null) {
                System.out.println(data.getPath());
            }
        }


    }
}
