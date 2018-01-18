package com.lt.integrate.frame.demo.four;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;


import com.lt.integrate.frame.demo.R;
import com.lt.integrate.frame.demo.four.adapter.ListViewAdapter;
import com.lt.integrate.frame.demo.four.adapter.ViewPagerAdapter;
import com.lt.integrate.frame.scrollview.GroupScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KyoWang on 2016/06/03 .
 * 项目有一个页面，涉及到了ViewPager，MapView，ListView，也就是说在一个页面中，会有这三个View，
 * 很明显，屏幕无法完全显示，需要ScrollView来做一下支援，就引入了ScrollView作为外层的容器。
 * 但是由于这个页面的数据展示需要做到用户手动下拉刷新，于是又引入了官方的SwipeRefreshLayout。
 *
 * 问题：
 * 加入了ScrollView和SwipeRefreshLayout之后引入了新的问题，就是各个控件之间的事件冲突，
 * 嵌套在ScrollView中的ViewPager、MapView、ListView都需要能够正确的处理点击事件，特别是ListView，
 * 需求要求它在ScrollView中可以滑动，两种滑动混淆在一起，不是特别好处理。

 解决滑动冲突的思路
 在ViewGroup中有个方法叫requestDisallowInterceptTouchEvent(boolean disallowIntercept)，
 这个方法可以用来控制该ViewGroup是否截断点击事件。我们解决滑动冲突的时候，其实就是在某个时机去调用这个方法，
 让父布局不截断点击事件，将点击事件传递到子View，让相关的子View去处理。
 下面就是关于在项目中处理各种点击事件冲突的一些例子和思考。处理的方法只是提供一种思路，可能并不是最优的方法，肯定存在其他思路的解决方案。
 以下处理滑动冲突的方案都是在子View的OnTouchListener里面进行处理，并没有去复写控件的点击事件处理过程，在使用中还是比较方便的。


 MapView地图页面滑动冲突
 MapView与ScrollView的冲突主要在于，当用户点击到MapView地图并且滑动的时候，希望由地图Map去处理点击事件，并包括后续的滑动事件、双手指缩放地图等等。
 在ScrollView中，是会默认截断点击事件的，导致用户点击到地图后，地图基本是没有反应，更别谈双手指缩放地图了。
 用户手指点击到地图，并且滑动的时候，很难确定用户是要ScrollView上下滑动还是操控地图内容滑动，所以我简单的认为，
 只要用户手指点击到地图，就是要对地图进行操作；当用户手指抬起，就认为用户不需要操作地图了。
 解决思路也很简单，就是在用户点击到地图或者滑动地图时候，让ScrollView不截断点击事件，并传递给子View处理，
 也就是地图去处理点击事件；当用户手指抬起的时候，将ScrollView的状态恢复至之前的状态，也就是ScrollView可以截断点击事件。

 ViewPager滑动冲突解决
 在这个项目中，ViewPager在页面最顶层，如果只是ScrollView里面嵌套了ViewPager，因为这两个控件是不同方向的滑动事件，所以基本不会出现冲突。
 但是由于引入了SwipeRefreshLayout，我发现在滑动ViewPager的时候，很容易就触发了SwipeRefreshLayout的下来刷新，进而有可能阻断了ViewPager的左右滑动效果，体验很不好。而且在滑动ViewPager的过程中，用户滑动肯定不是一直水平的，会有一定程度向上向下的滑动。
 ViewPager处理冲突和地图处理冲突有些不同，因为当用户点击到ViewPager，在滑动过程中，基本就可以猜测到用户是想左右滑动ViewPager还是上下滑动ScrollView（或者下拉刷新），这就不能像地图一样，在点击到ViewPager就禁止ScrollView截断点击事件（或者SwipeRefreshLayout下拉刷新功能），需要在滑动过程中做出判断。
 解决思路就是，设定一个阈值，一旦用户在X轴也就是横向滑动距离超过这个阈值，我就认为用户是要左右滑动ViewPager，就禁止ScrollView截断点击事件同时设置SwipeRefreshLayout不能下拉刷新。当用户抬起手指，就认为用户对ViewPager的操作已经完毕，将ScrollView和SwipeRefreshLayout状态恢复。

 ListView滑动冲突解决
 在ScrollView中嵌套ListView，会出现各种各样奇怪的问题。比如说ListView显示有问题，可能才一两个Item那么高，并没有完全的展开。网上流传解决这种问题的方法会有两种。
 根据展示数据的个数乘以每一个Item的高度，计算出ListView的总体高度，然后动态的设置ListView的高度
 复写ListView的onMeasure(int widthMeasureSpec, int heightMeasureSpec)方法，让ListView完全展开
 这两种方法都可以解决ListView展示不完全的问题，而且也可以滑动（其实是使用ScrollView的滑动效果），但是有一个最大的遗憾，就是ListView里面的View不能复用了。因为这两种方法都是算出了ListView的全部高度，然后将ListView控件的高度设置成这个高度，这样的话，ListView就相当于一个LinearLayout的布局了，失去了复用View的优势，而且在某些场景可能还没有LinearLayout好用，更甚的是，如果有大量图片的话，很容易就OOM了，这是在研发过程中最不希望看见的。
 可以参考一下美团，美团的首页，就是一个ScrollView，下滑的时候会发现，并不能无限向下滑动，到了底部会提醒跳转到一个二级页面去查看全部的团购信息。这是处理ScrollView里面嵌套类似ListView列表布局的时候的一种解决方案。
 但是在我遇见的这个项目里面，并不能这样处理。
 上面的提到的两种解决思路很明确，如果想要ListView正常展示就需要确定ListView的高度，这个很重要。
 所以首先，我需要在布局文件中设置ListView的高度，是一个明确的数值。设置高度之后，如果ListView中的数据的Item总高度超过ListView所设置的高度，就可以复用View了。但是这只是解决了ListView的显示问题，ListView与ScrollView的滑动冲突，并没有解决。
 要解决滑动的冲突，最主要的是确定禁止ScrollView截断点击事件的时机，然后来分析有哪些时机。
 ScrollView在未滑动到底部时候，如果点击并滑动ListView时候，ListView是不能滑动的，不禁止。
 如果ScrollView滑动到底部，且ListView已经到顶部，继续下拉ListView，其实会拉动ScrollView，不禁止。
 如果ScrollView滑动到底部，用户向上滑，ListView滑动，禁止ScrollView截断点击事件能力
 很明显，在判断禁止ScrollView截断点击事件时机的时候，需要知道ScrollView是否滑动到了底部。于是，重写了ScrollView的ScrollChanged()方法，来判断ScrollView是否滑动到底部(SDK API 23版本中ScrollView可以设置setOnScrollChangeListener()来监听滑动的变化，但是之前版本不支持，为了兼容，自己需要重写)。
 */
public class FourActivity extends AppCompatActivity {

    /**
     * viewpager横向滑动的阈值
     */
    private static final int THRESHOLD_X_VIEW_PAGER = 60;
    /**
     * listview竖向滑动的阈值
     */
    private static final int THRESHOLD_Y_LIST_VIEW = 20;

    /**
     * 下拉刷新控件
     */
    private SwipeRefreshLayout mRefreshLayout;
    private GroupScrollView mScrollView;
    private ViewPager mViewPager;
    private ListView mListView;

    private ViewPagerAdapter mViewPagerAdapter;
    private ListViewAdapter mListViewAdapter;

    private boolean isSvToBottom = false;

    private float mLastX;
    private float mLastY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);
        initView();
        initAction();
        fixSlideConflict();
    }

    private void initView() {
        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.main_refresh_layout);
        mScrollView = (GroupScrollView) findViewById(R.id.main_scroll_view);
        mViewPager = (ViewPager) findViewById(R.id.main_viewpager);
        mListView = (ListView) findViewById(R.id.main_list_view);

        mListViewAdapter = new ListViewAdapter(this, getListViewData());
        mListView.setAdapter(mListViewAdapter);

        mViewPagerAdapter = new ViewPagerAdapter(this, getViewPagerData());
        mViewPager.setAdapter(mViewPagerAdapter);

    }

    private List<String> getListViewData() {
        List<String> data = new ArrayList<>();
        for(int i = 1; i <= 20; i ++) {
            data.add(i + " item");
        }

        return data;
    }

    private List<String> getViewPagerData() {
        List<String> data = new ArrayList<>();
        for(int i = 1; i <= 5; i ++) {
            data.add("page " + i);
        }

        return data;
    }

    private void initAction() {
        //模拟下拉刷新，0.5秒后，下拉刷新状态视图消失
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mRefreshLayout.setRefreshing(false);
                    }
                }, 500);
            }
        });

        mScrollView.setScrollToBottomListener(new GroupScrollView.OnScrollToBottomListener() {
            @Override
            public void onScrollToBottom() {
                isSvToBottom = true;
            }

            @Override
            public void onNotScrollToBottom() {
                isSvToBottom = false;
            }
        });
    }

    private void fixSlideConflict() {
        // ViewPager滑动冲突解决
        mViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                if(action == MotionEvent.ACTION_DOWN) {
                    // 记录点击到ViewPager时候，手指的X坐标
                    mLastX = event.getX();
                }
                if(action == MotionEvent.ACTION_MOVE) {
                    // 超过阈值，禁止SwipeRefreshLayout下拉刷新，禁止ScrollView截断点击事件
                    if(Math.abs(event.getX() - mLastX) > THRESHOLD_X_VIEW_PAGER) {
                        mRefreshLayout.setEnabled(false);
                        mScrollView.requestDisallowInterceptTouchEvent(true);
                    }
                }
                // 用户抬起手指，恢复父布局状态
                if(action == MotionEvent.ACTION_UP) {
                    mRefreshLayout.setEnabled(true);
                    mScrollView.requestDisallowInterceptTouchEvent(false);
                }
                return false;
            }
        });

        // ListView滑动冲突解决
        mListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();

                if(action == MotionEvent.ACTION_DOWN) {
                    mLastY = event.getY();
                }
                if(action == MotionEvent.ACTION_MOVE) {
                    int top = mListView.getChildAt(0).getTop();
                    float nowY = event.getY();
                    if(!isSvToBottom) {
                        // 允许scrollview拦截点击事件, scrollView滑动
                        mScrollView.requestDisallowInterceptTouchEvent(false);
                    } else if(top == 0 && nowY - mLastY > THRESHOLD_Y_LIST_VIEW) {
                        // 允许scrollview拦截点击事件, scrollView滑动
                        mScrollView.requestDisallowInterceptTouchEvent(false);
                    } else {
                        // 不允许scrollview拦截点击事件， listView滑动
                        mScrollView.requestDisallowInterceptTouchEvent(true);
                    }
                }
                return false;
            }
        });

        //MapView滑动事件冲突解决
        /*mMapView.getChildAt(0).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP){
                    //允许ScrollView截断点击事件，ScrollView可滑动
                    mScrollView.requestDisallowInterceptTouchEvent(false);
                }else{
                    //不允许ScrollView截断点击事件，点击事件由子View处理
                    mScrollView.requestDisallowInterceptTouchEvent(true);
                }
                return false;
            }
        });*/
    }
}
