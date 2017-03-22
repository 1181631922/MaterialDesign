package com.fanyafeng.materialdesign.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.fanyafeng.materialdesign.R;
import com.fanyafeng.materialdesign.BaseActivity;
import com.fanyafeng.materialdesign.util.DpPxConvert;
import com.fanyafeng.materialdesign.util.MyUtils;
import com.fanyafeng.materialdesign.view.MyScrollView;

//需要搭配Baseactivity，这里默认为Baseactivity,并且默认BaseActivity为包名的根目录
public class NestActivity extends BaseActivity {
    private MyScrollView layoutRoot;
    private MyScrollView layoutChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nest);
        //这里默认使用的是toolbar的左上角标题，如果需要使用的标题为中心的采用下方注释的代码，将此注释掉即可
        title = getString(R.string.title_activity_nest);

        initView();
        initData();
    }

    //初始化UI控件
    private void initView() {
        layoutRoot = (MyScrollView) findViewById(R.id.layoutRoot);
        layoutChild = (MyScrollView) findViewById(R.id.layoutChild);
        layoutRoot.setMyScrollView(layoutChild);
//        layoutRoot.setOnScrollListener(new MyScrollView.OnScrollListener() {
//            @Override
//            public void OnScroll(int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
////                Log.d("NestActivity", "高度：" + DpPxConvert.dip2px(NestActivity.this, 200f));
////                Log.d("NestActivity", "scrollX:" + scrollX + ",scrollY:" + scrollY);
//                if (scrollY >= DpPxConvert.dip2px(NestActivity.this, 200f)) {//子scroll监听手势，处理滑动
////                    layoutRoot.dispatchTouchEvent();
//                    layoutRoot.setMyScrollView(layoutChild);
//                } else {
//
//                }
//            }
//        });
    }

    //初始化数据
    private void initData() {

    }

}
