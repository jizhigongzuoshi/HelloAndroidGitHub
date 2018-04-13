package cn.com.focus.helloandroidgithub.activity.customview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by MrLu on 2018/1/22.
 */

public class MyLinearLayout extends FrameLayout{
    public MyLinearLayout(Context context) {
        super(context);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //setMeasuredDimension(1920, 1080);
    }

    @Override
    protected void measureChild(View child, int parentWidthMeasureSpec,
                                int parentHeightMeasureSpec) {
        ViewGroup.LayoutParams lp = child.getLayoutParams();

        int childWidthMeasureSpec;
        int childHeightMeasureSpec;

        childWidthMeasureSpec = getChildMeasureSpec(parentWidthMeasureSpec, 0
                , lp.width);
        //final int verticalPadding = mPaddingTop + mPaddingBottom;
        System.out.println("child = [" + child + "], parentWidthMeasureSpec = [" + MeasureSpec.getSize(parentHeightMeasureSpec));
        childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(
               0,
                MeasureSpec.UNSPECIFIED);

        child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
    }
}
