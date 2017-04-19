package com.fanyafeng.materialdesign.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.fanyafeng.materialdesign.Constant.MaterialDesignConstant;
import com.fanyafeng.materialdesign.R;
import com.fanyafeng.materialdesign.BaseActivity;
import com.fanyafeng.materialdesign.adapter.RVAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//需要搭配Baseactivity，这里默认为Baseactivity,并且默认BaseActivity为包名的根目录
public class BehaviorActivity extends BaseActivity {
    private RecyclerView rvBehavior;
    private RVAdapter rvAdapter;
    private List<String> stringList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_behavior);
        //这里默认使用的是toolbar的左上角标题，如果需要使用的标题为中心的采用下方注释的代码，将此注释掉即可
        title = getString(R.string.title_activity_behavior);

//        initView();
//        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //toolbar_center_title.setText(getString(R.string.title_activity_behavior));
    }

    //初始化UI控件
    private void initView() {
//        rvBehavior = (RecyclerView) findViewById(R.id.rvBehavior);
//        rvBehavior.setLayoutManager(new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false));
//        stringList = new ArrayList<>();
//        stringList = Arrays.asList(MaterialDesignConstant.imageList);
//        rvAdapter = new RVAdapter(this, stringList);
//        rvBehavior.setAdapter(rvAdapter);
    }

    //初始化数据
    private void initData() {

    }

}
