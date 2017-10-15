package cn.com.focus.helloandroidgithub.activity.launchmode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.com.focus.helloandroidgithub.R;
import cn.com.focus.helloandroidgithub.activity.launchmode.singletask.SingleTaskActivity;
import cn.com.focus.helloandroidgithub.activity.launchmode.singletop.SingleTopActivity;
import cn.com.focus.helloandroidgithub.framework.LZBaseActivity;

public class LaunchModeActivity extends LZBaseActivity {
    private ListView listView = null;
    private List<BindItem> bindItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {
        listView = $Id(listView, R.id.lv);
    }

    @Override
    public void initData() {
        bindItems = new ArrayList<>();
        BindItem bindItemSingleTop = new BindItem("SingleTop", SingleTopActivity.class);
        BindItem bindItemSingleTask = new BindItem("SingleTask", SingleTaskActivity.class);
        bindItems.add(bindItemSingleTop);
        bindItems.add(bindItemSingleTask);

        ArrayAdapter<BindItem> adapter = new ArrayAdapter<BindItem>(this, android.R.layout.simple_list_item_1, bindItems);
        listView.setAdapter(adapter);
        listView.addHeaderView(View.inflate(this, R.layout.activity_singletask, null));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(LaunchModeActivity.this, bindItems.get(position - listView.getHeaderViewsCount()).cls));
            }
        });
    }

    @Override
    public int layoutResID() {
        return R.layout.activity_launchmode;
    }

}
