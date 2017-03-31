package com.fanyafeng.materialdesign.activity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageButton;

import com.fanyafeng.materialdesign.R;
import com.fanyafeng.materialdesign.BaseActivity;
import com.fanyafeng.materialdesign.view.FocusView;

//需要搭配Baseactivity，这里默认为Baseactivity,并且默认BaseActivity为包名的根目录
public class FocusActivity extends BaseActivity {
    private ImageButton tour;
    private ImageButton tv;
    private ImageButton ad1;
    private ImageButton cate;
    private ImageButton weather;
    private ImageButton ad2;
    private ImageButton news;
    private ImageButton appStore;
    private ImageButton video;
    private FocusView focusView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_focus);
        //这里默认使用的是toolbar的左上角标题，如果需要使用的标题为中心的采用下方注释的代码，将此注释掉即可
        title = getString(R.string.title_activity_focus);

        initView();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //toolbar_center_title.setText(getString(R.string.title_activity_focus));
    }

    //初始化UI控件
    private void initView() {
        tv = (ImageButton) findViewById(R.id.local_tv);
        tour = (ImageButton) findViewById(R.id.local_tour);
        ad1 = (ImageButton) findViewById(R.id.local_ad1);
        ad2 = (ImageButton) findViewById(R.id.local_ad2);
        cate = (ImageButton) findViewById(R.id.local_cate);
        weather = (ImageButton) findViewById(R.id.local_weather);
        news = (ImageButton) findViewById(R.id.local_news);
        appStore = (ImageButton) findViewById(R.id.local_app_store);
        video = (ImageButton) findViewById(R.id.local_video);
        focusView = (FocusView) findViewById(R.id.focus_view);

        tv.setOnFocusChangeListener(mOnFocusChangeListener);
        tour.setOnFocusChangeListener(mOnFocusChangeListener);
        ad1.setOnFocusChangeListener(mOnFocusChangeListener);
        ad2.setOnFocusChangeListener(mOnFocusChangeListener);
        cate.setOnFocusChangeListener(mOnFocusChangeListener);
        weather.setOnFocusChangeListener(mOnFocusChangeListener);
        news.setOnFocusChangeListener(mOnFocusChangeListener);
        appStore.setOnFocusChangeListener(mOnFocusChangeListener);
        video.setOnFocusChangeListener(mOnFocusChangeListener);
    }

    //初始化数据
    private void initData() {

    }

    public View.OnFocusChangeListener mOnFocusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                enlargeAnim(v);
                focusView.setVisibility(View.VISIBLE);
                focusView.setAnchorView(v);
            } else {
                reduceAnim(v);
            }
        }
    };

    public void enlargeAnim(View v) {
        Animation a = android.view.animation.AnimationUtils.loadAnimation(v.getContext(), R.anim.uikit_enlarge);
        a.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }
        });
        a.setFillAfter(true);
        v.clearAnimation();
        v.setAnimation(a);
        v.bringToFront();
        a.start();
    }

    public void reduceAnim(View v) {
        Animation a = android.view.animation.AnimationUtils.loadAnimation(v.getContext(), R.anim.uikit_reduce);
        a.setFillAfter(true);
        v.clearAnimation();
        v.startAnimation(a);
        a.start();
    }
}
