package com.lt.integrate.frame.recycleview;

import android.view.View;

/**
 * Created by iclick on 2018/01/18.
 * RecyclerView/ListView/GridView 滑动加载下一页时的回调接口
 */
public interface OnListLoadNextPageListener {

    /**
     * 开始加载下一页
     *
     * @param view 当前RecyclerView/ListView/GridView
     */
    void onLoadNextPage(View view);
}