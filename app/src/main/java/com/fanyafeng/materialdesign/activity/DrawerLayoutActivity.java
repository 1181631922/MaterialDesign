package com.fanyafeng.materialdesign.activity;

import android.net.Uri;
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

import com.facebook.drawee.view.SimpleDraweeView;
import com.fanyafeng.materialdesign.BaseActivity;
import com.fanyafeng.materialdesign.R;

public class DrawerLayoutActivity extends BaseActivity {
    private SimpleDraweeView sdvDrawerHead;

    private DrawerLayout layoutDrawer;
    private NavigationView layoutNavigationView;

    private final static String imageUri = "data:image/jpeg;base64,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";

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
        sdvDrawerHead = (SimpleDraweeView) layoutNavigationView.getHeaderView(0).findViewById(R.id.sdvDrawerHead);
        sdvDrawerHead.setImageURI(Uri.parse(imageUri));
        sdvDrawerHead.setAspectRatio(1.33f);
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
