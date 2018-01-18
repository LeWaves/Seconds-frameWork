package com.lt.integrate.frame.demo.six;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lt.integrate.frame.demo.R;
import com.lt.integrate.frame.scrollview.SmartScrollView;


/**
 * Created by PC on 2017/5/31.
 */

public class SixActivity extends AppCompatActivity {

    private SmartScrollView scrollView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_six);
        init();
    }

    private void init() {
        initFindViewId();
        initListener();
    }

    private void initFindViewId() {
        scrollView = (SmartScrollView) findViewById(R.id.scrollView);
    }

    private void initListener() {
        scrollView.setScanScrollChangedListener(new SmartScrollView.ISmartScrollChangedListener() {
            @Override
            public void onScrolledToBottom() {
                //Toast.makeText(SixActivity.this,"滑到底部呢",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onScrolledToTop() {
                //Toast.makeText(SixActivity.this,"滑到顶部呢",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
