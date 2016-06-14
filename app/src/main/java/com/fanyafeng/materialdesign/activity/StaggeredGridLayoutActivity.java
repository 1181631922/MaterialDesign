package com.fanyafeng.materialdesign.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.andview.refreshview.XRefreshView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.fanyafeng.materialdesign.BaseActivity;
import com.fanyafeng.materialdesign.Constant.MaterialDesignConstant;
import com.fanyafeng.materialdesign.R;
import com.fanyafeng.materialdesign.adapter.RVAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StaggeredGridLayoutActivity extends BaseActivity {

    private XRefreshView xrvStaggerRefresh;
    private RecyclerView rvStagger;

    private List<String> stringList;
    private RVAdapter rvAdapter;

    private SimpleDraweeView sdvStaggerTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staggered_grid_layout);

        title = "测试瀑布流";

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        initView();
        initData();
    }

    private void initView() {
//        xrvStaggerRefresh = (XRefreshView) findViewById(R.id.xrvStaggerRefresh);
//        xrvStaggerRefresh.setMoveForHorizontal(true);
//        xrvStaggerRefresh.setPullRefreshEnable(false);
//        xrvStaggerRefresh.setPullLoadEnable(false);
        rvStagger = (RecyclerView) findViewById(R.id.rvStagger);
        rvStagger.setHasFixedSize(true);
        sdvStaggerTop = (SimpleDraweeView) findViewById(R.id.sdvStaggerTop);
    }

    private void initData() {
        sdvStaggerTop.setImageURI(Uri.parse("http://img0.imgtn.bdimg.com/it/u=3808587152,3416392106&fm=21&gp=0.jpg"));
        stringList = new ArrayList<>();
        stringList = Arrays.asList(MaterialDesignConstant.imageList);
        rvAdapter = new RVAdapter(this, stringList);
        rvStagger.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        rvStagger.setAdapter(rvAdapter);
    }

}
