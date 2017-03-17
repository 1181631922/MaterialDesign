package com.fanyafeng.materialdesign.activity;

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
import android.view.View;

import com.fanyafeng.materialdesign.R;
import com.fanyafeng.materialdesign.BaseActivity;
import com.fanyafeng.materialdesign.fragment.TabLayoutFragment;

import java.util.ArrayList;
import java.util.List;


//需要搭配Baseactivity，这里默认为Baseactivity,并且默认BaseActivity为包名的根目录
public class SwitchListActivity extends BaseActivity implements ViewPager.OnPageChangeListener{

    private TabLayout layoutTab;
    private ViewPager viewpagerTab;

    private String[] stringList = new String[]{"LinearLayout", "GridView", "ListView", "LinearLayout", "StaggeredGridLayout"};
    private List<Fragment> fragmentList;
    private MyViewPagerAdapter myViewPagerAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_list);

        initView();
        initData();
    }

    private void initView() {
        layoutTab = (TabLayout) findViewById(R.id.id_stickynavlayout_indicator);
        viewpagerTab = (ViewPager) findViewById(R.id.id_stickynavlayout_viewpager);
        fragmentList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            TabLayoutFragment tabLayoutFragment = new TabLayoutFragment();
            Bundle bundle = new Bundle();
            bundle.putString("flag", String.valueOf(i));
            tabLayoutFragment.setArguments(bundle);
            fragmentList.add(tabLayoutFragment);
        }
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
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        toolbar.setTitle(stringList[position]);
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
