package cn.com.focus.helloandroidgithub.activity.launchmode;

import android.app.Activity;

/**
 * Created by MrLu on 2017/10/14.
 */

public class BindItem {
    public String name;
    public Class<? extends Activity> cls;

    public BindItem(String name, Class<? extends Activity> cls) {
        this.name = name;
        this.cls = cls;
    }
    @Override
    public String toString() {
        return name;
    }
}
