package com.lt.integrate.frame.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lt.integrate.frame.demo.first.FirstActivity;
import com.lt.integrate.frame.demo.five.FiveActivity;
import com.lt.integrate.frame.demo.four.FourActivity;
import com.lt.integrate.frame.demo.second.SecondActivity;
import com.lt.integrate.frame.demo.six.SixActivity;
import com.lt.integrate.frame.demo.third.ThirdActivity;

/**
 * Created by iclick on 2018/1/16.
 */

public class ScrollViewActivity  extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_layout_activity);
        findViewById(R.id.onBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void onScrollDetails(View v){
           startActivity(new Intent(ScrollViewActivity.this, FirstActivity.class));
    }
    public void onScrollSliding(View v){
        startActivity(new Intent(ScrollViewActivity.this, SecondActivity.class));
    }
    public void onScrollOrRecyeleView(View v){
        startActivity(new Intent(ScrollViewActivity.this, ThirdActivity.class));
    }
    public void onScrollOrViewPage(View v){
        startActivity(new Intent(ScrollViewActivity.this, FourActivity.class));
    }
    public void onScrollOrViewPageOrListView(View v){
        startActivity(new Intent(ScrollViewActivity.this, FiveActivity.class));
    }
    public void onScrollTopOrBottom(View v){
        startActivity(new Intent(ScrollViewActivity.this, SixActivity.class));
    }

    public void onRecycleView(View v){
        startActivity(new Intent(ScrollViewActivity.this, RecycleViewUtilsActivity.class));
    }






}
