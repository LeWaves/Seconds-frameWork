package com.lt.integrate.frame.demo.five;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


import com.lt.integrate.frame.demo.R;
import com.lt.integrate.frame.demo.four.adapter.ViewPagerAdapter;
import com.lt.integrate.frame.scrollview.viewpage.GroupViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 2017/5/31.
 * ScrollView 里面嵌套ViewPager导致的滑动冲突
 *
 * 外部解决法
 * 从 父View ScrollView着手，重写 OnInterceptTouchEvent方法，在上下滑动的时候拦截事件，
 * 在左右滑动的时候不拦截事件，返回 false，这样确保子View 的dispatchTouchEvent方法会被调用
 *
 * 内部解决法
 * 通过requestDisallowInterceptTouchEvent(true)方法来影响父View是否拦截事件，
 * 我们通过重写ViewPager的 dispatchTouchEvent（）方法，在左右滑动的时候请求父View ScrollView不要拦截事件，其他的时候由子View 拦截事件
 *
 *
 * ViewPager里面嵌套ViewPager导致的滑动冲突
 * 内部解决法
 * 从子View ViewPager着手，重写 子View的 dispatchTouchEvent方法，在子 View需要拦截的时候进行拦截，否则交给父View处理
 */


public class FiveActivity extends AppCompatActivity {

    private GroupViewPager viewPager;
    private ViewPagerAdapter mViewPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five);
        init();
    }

    private void init() {
        viewPager = (GroupViewPager) findViewById(R.id.viewpager);
        mViewPagerAdapter = new ViewPagerAdapter(this, getViewPagerData());
        viewPager.setAdapter(mViewPagerAdapter);
    }

    private List<String> getViewPagerData() {
        List<String> data = new ArrayList<>();
        for(int i = 1; i <= 5; i ++) {
            data.add("viewPager " + i);
        }
        return data;
    }

}
