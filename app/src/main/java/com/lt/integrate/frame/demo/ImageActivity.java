package com.lt.integrate.frame.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.lt.integrate.frame.img.ImageLoader;
import com.lt.integrate.frame.view.RoundImageView;

/**
 * Created by iclick on 2017/12/29.
 */

public class ImageActivity extends AppCompatActivity {
    RoundImageView imageView;
    String url="https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1426225689,2196820510&fm=27&gp=0.jpg";
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.img_layout_activity);
        imageView = (RoundImageView) findViewById(R.id.imageView);
        findViewById(R.id.onBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ImageLoader.display(this,imageView,url);

    }

    public void DefaultClick(View view){
        imageView.setType(RoundImageView.TYPE_DEFAULT);
    }
    public void RoundClick(View view){
        imageView.setType(RoundImageView.TYPE_ROUND);
    }
    public void CricleClick(View view){
        imageView.setType(RoundImageView.TYPE_CIRCLE);
    }

}
