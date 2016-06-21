package com.fanyafeng.materialdesign.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.fanyafeng.materialdesign.BaseActivity;
import com.fanyafeng.materialdesign.Constant.MaterialDesignConstant;
import com.fanyafeng.materialdesign.R;
import com.fanyafeng.materialdesign.adapter.RVAdapter;
import com.fanyafeng.materialdesign.fragment.ViewPagerFragment;
import com.fanyafeng.materialdesign.util.FitScreenUtil;
import com.fanyafeng.materialdesign.util.MyUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StaggeredActivity extends BaseActivity {

    private final static String[] imgList = {"http://img3.imgtn.bdimg.com/it/u=1592877738,3666022423&fm=21&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=3455823481,3036827216&fm=21&gp=0.jpg",
            "data:image/jpeg;base64,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",
            "http://img3.imgtn.bdimg.com/it/u=270179915,2007129802&fm=21&gp=0.jpg",
            "http://img3.imgtn.bdimg.com/it/u=1255836822,4097950891&fm=21&gp=0.jpg"};

    private List<Fragment> fragmentList;
    private ViewPager staggerViewpager;

    private RecyclerView rvStagger;

    private List<String> stringList;
    private RVAdapter rvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staggered);
        title = "测试viewpager和toolbar的嵌套";
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
        staggerViewpager = (ViewPager) findViewById(R.id.staggerViewpager);
        fragmentList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            ViewPagerFragment viewPagerFragment = new ViewPagerFragment();
            Bundle bundle = new Bundle();
            bundle.putString("img", imgList[i]);
            viewPagerFragment.setArguments(bundle);
            fragmentList.add(viewPagerFragment);
        }
        staggerViewpager.setAdapter(new PagerAdapter(getSupportFragmentManager(), fragmentList));
        staggerViewpager.setCurrentItem(0);
        rvStagger = (RecyclerView) findViewById(R.id.rvStagger);
        rvStagger.setHasFixedSize(true);
    }

    private void initData() {
        stringList = new ArrayList<>();
        stringList = Arrays.asList(MaterialDesignConstant.imageList);
        rvAdapter = new RVAdapter(this, stringList);
        rvStagger.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        rvStagger.setAdapter(rvAdapter);
    }

    class PagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragmentList;

        public PagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
            super(fm);
            this.fragmentList = fragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }

}
