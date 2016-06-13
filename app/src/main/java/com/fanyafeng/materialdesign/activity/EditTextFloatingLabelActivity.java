package com.fanyafeng.materialdesign.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.fanyafeng.materialdesign.BaseActivity;
import com.fanyafeng.materialdesign.R;

public class EditTextFloatingLabelActivity extends BaseActivity {
    private TextInputLayout layoutEtEmail;
    private EditText etEmail;

    private TextInputLayout layoutEtPwd;
    private EditText etPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text_floating_label);

        title = "测试floatinglabel";

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
        layoutEtEmail = (TextInputLayout) findViewById(R.id.layoutEtEmail);
        etEmail = (EditText) findViewById(R.id.etEmail);

        layoutEtPwd = (TextInputLayout) findViewById(R.id.layoutEtPwd);
        etPwd = (EditText) findViewById(R.id.etPwd);

    }

    private void initData() {
        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = etEmail.getText().toString().trim();
                if (!text.equals("") && text != null) {
                    layoutEtEmail.setErrorEnabled(false);
                    layoutEtEmail.setError(null);
                } else {
                    layoutEtEmail.setErrorEnabled(true);
                    layoutEtEmail.setError("格式错误");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


//        layoutEtPwd.setErrorEnabled(true);
//        layoutEtPwd.setError("密码为数字");
    }

}
