package com.fanyafeng.materialdesign;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.fanyafeng.materialdesign.R;

import java.util.Objects;

public class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    protected Toolbar toolbar;
    protected FloatingActionButton fab;
    protected TextView toolbar_center_title;
    protected boolean isShowToolbar = true;
    protected boolean isSetNavigationIcon = true;
    protected boolean isSetLogo = false;
    protected boolean isShowEmail = true;
    protected String title;
    protected String centertitle;
    protected String subtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar_center_title = (TextView) findViewById(R.id.toolbar_center_title);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        if (toolbar != null) {
            if (isShowToolbar) {
                setSupportActionBar(toolbar);
            } else {
                toolbar.setVisibility(View.GONE);
            }

            if (title != null && !title.equals("")) {
                toolbar.setTitle(title);
            }

            if (subtitle != null && !subtitle.equals("")) {
                toolbar.setSubtitle(subtitle);
            }
            if (isSetNavigationIcon) {
//                由于要兼容低版本，所以采用这个划杠的方法
                toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.back));
                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
            }
            if (isSetLogo) {
                toolbar.setLogo(getResources().getDrawable(R.drawable.menu));
            }

            if (toolbar_center_title != null) {
                if (centertitle != null && !centertitle.equals("")) {
                    toolbar_center_title.setText(centertitle);
                } else {
                    toolbar_center_title.setText("");
                }

            }

        }

        if (fab != null) {
            fab.setVisibility(isShowEmail ? View.VISIBLE : View.GONE);
        }

    }

}
