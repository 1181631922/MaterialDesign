package com.fanyafeng.materialdesign.activity;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
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

    private RecyclerView rvStagger;

    private List<String> stringList;
    private RVAdapter rvAdapter;

    private SimpleDraweeView sdvStaggerTop;

    private String imgUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staggered_grid_layout);

        title = "测试瀑布流";
        if (getIntent().getStringExtra("imgUrl") != null) {
            imgUrl = getIntent().getStringExtra("imgUrl");
        }

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
        rvStagger = (RecyclerView) findViewById(R.id.rvStagger);
        rvStagger.setHasFixedSize(true);
        sdvStaggerTop = (SimpleDraweeView) findViewById(R.id.sdvRvItem);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initData() {
        if (imgUrl != null && !imgUrl.equals("")) {
            ActivityOptions.makeSceneTransitionAnimation(this, sdvStaggerTop, "SimpleDraweeView");
            sdvStaggerTop.setImageURI(Uri.parse(imgUrl));
        } else {
            sdvStaggerTop.setImageURI(Uri.parse("http://img0.imgtn.bdimg.com/it/u=3808587152,3416392106&fm=21&gp=0.jpg"));
        }
        stringList = new ArrayList<>();
        stringList = Arrays.asList(MaterialDesignConstant.imageList);
        rvAdapter = new RVAdapter(this, stringList);
        rvStagger.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        rvStagger.setAdapter(rvAdapter);

        rvAdapter.setOnItemClickListener(new RVAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, String string, int position) {
                Intent intent = new Intent();
                intent.putExtra("imgUrl", string);
                intent.setClass(StaggeredGridLayoutActivity.this, StaggeredGridLayoutActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(StaggeredGridLayoutActivity.this, view, "SimpleDraweeView");
                ActivityCompat.startActivity(StaggeredGridLayoutActivity.this, intent, options.toBundle());
            }

            @Override
            public void onItemLongClickListener(View view, String string, int position) {

            }
        });
    }


}
