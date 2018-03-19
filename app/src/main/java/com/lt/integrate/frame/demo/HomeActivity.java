package com.lt.integrate.frame.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lt.integrate.frame.view.MultipleTextViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by iclick on 2017/9/30.
 */

public class HomeActivity extends AppCompatActivity {
    private MultipleTextViewGroup textViewGroup;
    private List<String> groupList = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity_layout);
        initData();
        initView();
    }

    private void initView() {
        textViewGroup = (MultipleTextViewGroup) findViewById(R.id.textViewGroup);
        textViewGroup.setMax_lins(1);
        textViewGroup.setTextViews(groupList);
    }

    private void initData() {
        groupList.add("网红美女");
        groupList.add("青春美女");
        groupList.add("素颜美女");
        groupList.add("混血美女");
        groupList.add("日韩美女");
        groupList.add("日韩大战美女");
    }

    public void onHttpRequest(View view){
      startActivity(new Intent(HomeActivity.this,DataRecycleActivity.class));
    }
    public void onUploadRequest(View view){
        startActivity(new Intent(HomeActivity.this,LoadingActivity.class));
    }
    public void onDownloadRequest(View view){
        startActivity(new Intent(HomeActivity.this,LoadingActivity.class));
    }
    public void onShowVideoRequest(View view){
        startActivity(new Intent(HomeActivity.this,VideoLayoutActivity.class));
    }
    public void onShowImgRequest(View view){
        startActivity(new Intent(HomeActivity.this,ImageActivity.class));
    }
    public void OpenGitHubHttp(View view){
        Intent it = new Intent(HomeActivity.this,DetailsActivity.class);
        it.putExtra("url","https://github.com/LeWaves/Seconds-framework");
        startActivity( it);
    }
    public void onShowPermissionRequest(View view){
        startActivity( new Intent(HomeActivity.this,PermissionActivity.class));
    }
    public void onShowScrollView(View view){
        startActivity( new Intent(HomeActivity.this,ScrollViewActivity.class));
    }



}
