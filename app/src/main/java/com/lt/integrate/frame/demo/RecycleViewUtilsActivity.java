package com.lt.integrate.frame.demo;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lt.integrate.frame.demo.recycle.EndlessGridLayoutActivity;
import com.lt.integrate.frame.demo.recycle.EndlessLinearLayoutActivity;
import com.lt.integrate.frame.demo.recycle.EndlessStaggeredGridLayoutActivity;
import com.lt.integrate.frame.demo.recycle.LinearLayoutActivity;

/**
 * Created by iclick on 2018/1/18.
 */

public class RecycleViewUtilsActivity  extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyecleview_utils_activity_layout);
        findViewById(R.id.onBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void  onLinearLayout(View v){
        startActivity(new Intent(this, LinearLayoutActivity.class));
    }

    public void  onEndlessLinearLayout(View v){
        startActivity(new Intent(this, EndlessLinearLayoutActivity.class));
    }
    public void  onEndlessGridLayout(View v){
        startActivity(new Intent(this, EndlessGridLayoutActivity.class));
    }
    public void  onEndlessStaggeredGridLayout(View v){
        startActivity(new Intent(this, EndlessStaggeredGridLayoutActivity.class));
    }




}
