package com.fanyafeng.materialdesign.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.fanyafeng.materialdesign.R;
import com.fanyafeng.materialdesign.BaseActivity;
import com.fanyafeng.materialdesign.fragment.TabLayoutFragment;
import com.fanyafeng.materialdesign.view.StickyNavLayout;

import java.util.ArrayList;
import java.util.List;


//需要搭配Baseactivity，这里默认为Baseactivity,并且默认BaseActivity为包名的根目录
public class SwitchListActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    private final static String TAG = "SwitchListActivity";

    private TabLayout layoutTab;
    private ViewPager viewpagerTab;

    private String[] stringList = new String[]{"LinearLayout", "GridView", "ListView"
//            , "LinearLayout", "StaggeredGridLayout"
    };
    private List<Fragment> fragmentList;
    private MyViewPagerAdapter myViewPagerAdapter;
    private StickyNavLayout layoutRoot;
    private RelativeLayout id_stickynavlayout_topview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_list);

        initView();
        initData();
    }

    private void initView() {
        layoutRoot = (StickyNavLayout) findViewById(R.id.layoutRoot);
        id_stickynavlayout_topview = (RelativeLayout) findViewById(R.id.id_stickynavlayout_topview);

        layoutTab = (TabLayout) findViewById(R.id.id_stickynavlayout_indicator);
        layoutTab.setTabTextColors(Color.WHITE, Color.GRAY);


        viewpagerTab = (ViewPager) findViewById(R.id.id_stickynavlayout_viewpager);
        fragmentList = new ArrayList<>();
        for (int i = 0; i < stringList.length; i++) {
            TabLayoutFragment tabLayoutFragment = new TabLayoutFragment();
            Bundle bundle = new Bundle();
            bundle.putString("flag", String.valueOf(i));
            tabLayoutFragment.setArguments(bundle);
            tabLayoutFragment.setTabLayoutCallBack(new TabLayoutFragment.TabLayoutCallBack() {
                @Override
                public void callBack(String tabPosition, int totalSize, int firstPosition, int lastPosition) {
                    Log.d(TAG, "tabPosition:" + tabPosition + ",totalSize:" + totalSize + ",lastPosition:" + lastPosition);
                    if (lastPosition == 35 && layoutTab.getSelectedTabPosition() < stringList.length - 1) {
                        layoutTab.getTabAt(Integer.parseInt(tabPosition) + 1).select();
                    }
                    if (firstPosition == 0 && layoutTab.getSelectedTabPosition() > 0) {
                        layoutTab.getTabAt(Integer.parseInt(tabPosition) - 1).select();
                    }
                }
            });
            fragmentList.add(tabLayoutFragment);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initData() {
        myViewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager(), stringList, fragmentList);
        viewpagerTab.setAdapter(myViewPagerAdapter);
        viewpagerTab.setOffscreenPageLimit(3);
        viewpagerTab.addOnPageChangeListener(this);
//        代码中优先级高于xml
//        layoutTab.setTabMode(TabLayout.MODE_SCROLLABLE);
        layoutTab.setupWithViewPager(viewpagerTab);
        layoutTab.setTabsFromPagerAdapter(myViewPagerAdapter);
//        layoutTab.getTabAt(3).select();初始position

        id_stickynavlayout_topview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "id_stickynavlayout_topview onclick");
            }
        });

//        id_stickynavlayout_topview.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                Log.d(TAG, "用户进行touch操作");
//                return false;
//            }
//        });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (toolbar != null) {
            toolbar.setTitle(stringList[position]);
        }
        Log.d(TAG, "onPageSelected：" + position);
        if (position == 0) {
            layoutRoot.setCanPullDown(true);
        } else {
            layoutRoot.setCanPullDown(false);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class MyViewPagerAdapter extends FragmentStatePagerAdapter {

        private String[] titleList;
        private List<Fragment> fragmentList;

        public MyViewPagerAdapter(FragmentManager fm, String[] titleList, List<Fragment> fragmentList) {
            super(fm);
            this.titleList = titleList;
            this.fragmentList = fragmentList;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleList[position];
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
