package com.fanyafeng.materialdesign.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.fanyafeng.materialdesign.BaseActivity;
import com.fanyafeng.materialdesign.R;

public class ToolbarExitUntilCollapsedActivity extends BaseActivity {
    private SimpleDraweeView sdvCollapsedTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar_exit_until_collapsed);

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
        sdvCollapsedTop = (SimpleDraweeView) findViewById(R.id.sdvCollapsedTop);
        sdvCollapsedTop.setImageURI(Uri.parse("http://img0.imgtn.bdimg.com/it/u=4136016998,4074341228&fm=21&gp=0.jpg"));
    }

    private void initData() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_collapsed, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.collasped1:
                toolbar.getMenu().getItem(1).setVisible(false);
                break;
            case R.id.collasped2:
                toolbar.getMenu().getItem(1).setVisible(true);
                break;
            case R.id.collasped3:
                toolbar.getMenu().getItem(0).setVisible(false);
                break;
            case R.id.collasped4:
                toolbar.getMenu().getItem(0).setVisible(true);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
