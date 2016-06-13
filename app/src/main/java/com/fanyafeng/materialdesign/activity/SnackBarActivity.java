package com.fanyafeng.materialdesign.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.fanyafeng.materialdesign.BaseActivity;
import com.fanyafeng.materialdesign.R;

public class SnackBarActivity extends BaseActivity {
    private Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack_bar);

        title = "测试snackbar";

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btnActionSnackbar:
                Snackbar.make(v, "提示用户的消息", Snackbar.LENGTH_LONG)
                        .setAction("Action", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(SnackBarActivity.this, "Action响应", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
                break;
            case R.id.btnOneActionSnackbar:
                Snackbar snackbar = Snackbar.make(v, "提示用户的消息", Snackbar.LENGTH_LONG);
                snackbar.setAction("Confirm", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                snackbar.getView().setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                snackbar.show();

                break;
            case R.id.btnTwoActionSnackbar:
                Snackbar.make(v, "提示用户的消息", Snackbar.LENGTH_LONG)
                        .setAction("Confirm", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        }).setActionTextColor(getResources().getColor(R.color.colorPrimary)).show();
                break;
            case R.id.btnCustomSnackbar:
                // TODO: 16/6/13 有坑,小心使用
                snackbar = Snackbar.make(getWindow().getDecorView(), "提示用户的消息提示用户的消息提示用户的消息提示用户的消息提示用户的消息提示用户的消息提示用户的消息提示用户的消息", Snackbar.LENGTH_LONG);
                snackbar.setAction("Confirm", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                snackbar.getView().setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                snackbar.getView().setX(100);
                snackbar.show();
                break;
        }
    }
}
