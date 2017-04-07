package com.fanyafeng.materialdesign.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.fanyafeng.materialdesign.Constant.MaterialDesignConstant;
import com.fanyafeng.materialdesign.R;
import com.fanyafeng.materialdesign.BaseActivity;
import com.fanyafeng.materialdesign.adapter.RVAdapter;
import com.fanyafeng.materialdesign.util.MySnapHelper;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

//需要搭配Baseactivity，这里默认为Baseactivity,并且默认BaseActivity为包名的根目录
public class SnapHelperActivity extends BaseActivity {
    private RecyclerView rvSnapHelper;
    private RVAdapter rvAdapter;
    private List<String> stringList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snap_helper);
        //这里默认使用的是toolbar的左上角标题，如果需要使用的标题为中心的采用下方注释的代码，将此注释掉即可
        title = getString(R.string.title_activity_snap_helper);

        initView();
        initData();
    }


    //初始化UI控件
    private void initView() {
        rvSnapHelper = (RecyclerView) findViewById(R.id.rvSnapHelper);
        rvSnapHelper.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        stringList = new ArrayList<>();
        stringList = Arrays.asList(MaterialDesignConstant.imageList);
        rvAdapter = new RVAdapter(this, stringList);
        rvSnapHelper.setAdapter(rvAdapter);
        rvAdapter.setRecyclerView(rvSnapHelper);

//        MySnapHelper mySnapHelper = new MySnapHelper();
//        mySnapHelper.attachToRecyclerView(rvSnapHelper);
    }

    //初始化数据
    private void initData() {

        try {
            InputStream inputStream = getAssets().open("subject.xm");
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document document = builder.parse(inputStream);
            Element element = document.getDocumentElement();

            String ctype = element.getElementsByTagName("ctype").item(0).getTextContent();
            String description = element.getElementsByTagName("description").item(0).getTextContent();
            String softwareVersion = element.getElementsByTagName("softwareVersion").item(0).getTextContent();
            String time = element.getElementsByTagName("time").item(0).getTextContent();
            String updateFile = element.getElementsByTagName("updateFile").item(0).getTextContent();
            String updateFileMd5 = element.getElementsByTagName("updateFileMd5").item(0).getTextContent();
            String updateFileSize = element.getElementsByTagName("updateFileSize").item(0).getTextContent();
            String updateFlag = element.getElementsByTagName("updateFlag").item(0).getTextContent();
            String updateMethod = element.getElementsByTagName("updateMethod").item(0).getTextContent();

            Toast.makeText(this, element.getElementsByTagName("updateFile").item(0).getTextContent(), Toast.LENGTH_SHORT).show();

            ((TextView) findViewById(R.id.tvTest)).setText(ctype + "\n" + description + "\n" + softwareVersion + "\n" + time + "\n" + updateFileMd5
                    + "\n" + updateFileSize + "\n" + updateFlag + "\n" + updateMethod);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
