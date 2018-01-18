package com.lt.integrate.frame.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


/**
 * Created by iclick on 2017/9/30.
 */

public class HomeActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity_layout);

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
