package com.fanyafeng.materialdesign.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.fanyafeng.materialdesign.BaseActivity;
import com.fanyafeng.materialdesign.R;

public class DrawerLayoutActivity extends BaseActivity {
    private DrawerLayout layoutDrawer;
    private NavigationView layoutNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);
        title = "测试抽屉效果";

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
        layoutDrawer = (DrawerLayout) findViewById(R.id.layoutDrawer);
        layoutNavigationView = (NavigationView) findViewById(R.id.layoutNavigationView);
//        占用navicon,由于base中设置被覆盖了
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, layoutDrawer, toolbar, R.string.app_name, R.string.app_name);
        actionBarDrawerToggle.syncState();
        layoutDrawer.setDrawerListener(actionBarDrawerToggle);
    }


    private void initData() {
        layoutNavigationView.inflateHeaderView(R.layout.layout_drawer_head);
        layoutNavigationView.inflateMenu(R.menu.menu_drawer_nav);
        onMenuCheck(layoutNavigationView);
    }

    private void onMenuCheck(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_menu_home:
                        Toast.makeText(DrawerLayoutActivity.this, "点击第一个", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_menu_categories:
                        break;
                    case R.id.nav_menu_feedback:
                        break;
                    case R.id.nav_menu_setting:
                        break;
                }
                item.setChecked(true);
                layoutDrawer.closeDrawers();
                return true;
            }
        });
    }

}
