package com.lt.integrate.frame.demo.first;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.lt.integrate.frame.demo.R;
import com.lt.integrate.frame.demo.first.fragment.CommentFragment;
import com.lt.integrate.frame.demo.first.fragment.ContentDetailFragment;
import com.lt.integrate.frame.demo.first.fragment.GoodsDetailFragment;
import com.lt.integrate.frame.view.CustomViewPager;


public class FirstActivity extends AppCompatActivity {

    private TabLayout tabs;
    private CustomViewPager viewpager;
    TextView detailView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_first);
        findViewById(R.id.onBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tabs = (TabLayout) findViewById(R.id.tabs);

        viewpager = (CustomViewPager) findViewById(R.id.viewpager);
        detailView = (TextView) findViewById(R.id.detailView);
        detailView.setVisibility(View.GONE);

        MinePagerAdapter minePagerAdapter = new MinePagerAdapter(getSupportFragmentManager());
        viewpager.setOffscreenPageLimit(3);
        viewpager.setAdapter(minePagerAdapter);
        tabs.setupWithViewPager(viewpager);

    }


    public  Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    tabs.setVisibility(View.GONE);
                    detailView.setVisibility(View.VISIBLE);
                    viewpager.setScanScroll(false);
                    break;
                case 1:
                    tabs.setVisibility(View.VISIBLE);
                    detailView.setVisibility(View.GONE);
                    viewpager.setScanScroll(true);
                    break;
            }
        }
    };

    /**
     * ViewPager的PagerAdapter
     */
    public class MinePagerAdapter extends FragmentPagerAdapter {
        Fragment[] fragments = new Fragment[]{GoodsDetailFragment.newInstance(), ContentDetailFragment.newInstance(), CommentFragment.newInstance()};
        String[] titles = new String[]{"商品", "详情", "评价"};

        public MinePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public int getCount() {
            return fragments.length;
        }
    }


}

