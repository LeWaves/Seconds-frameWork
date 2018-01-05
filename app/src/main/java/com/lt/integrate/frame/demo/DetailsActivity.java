package com.lt.integrate.frame.demo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lt.integrate.frame.view.ProgressBarWebView;

/**
 * Created by iclick on 2017/12/7.
 */

public class DetailsActivity extends AppCompatActivity {
    ProgressBarWebView mWebView;
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity_layout);
        widgetListener();

    }

    private void widgetListener() {
        findViewById(R.id.onBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mWebView = (ProgressBarWebView) findViewById(R.id.mWebView);
        mWebView.setProgressColors(Color.RED,Color.YELLOW);
        //mWebView.setProgressColors(Color.RED, Color.BLACK, Color.DKGRAY,Color.YELLOW);
        mWebView.loadUrl(getIntent().getStringExtra("url"));
    }
}
