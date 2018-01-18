package com.lt.integrate.frame.demo.first.fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.lt.integrate.frame.demo.R;
import com.lt.integrate.frame.demo.first.FirstActivity;
import com.lt.integrate.frame.demo.utils.GlideImageLoader;
import com.lt.integrate.frame.scrollview.details.PageBehavior;
import com.lt.integrate.frame.scrollview.details.PageContainer;
import com.lt.integrate.frame.view.ProgressBarWebView;
import com.youth.banner.Banner;

import java.util.ArrayList;



/**
 * A simple {@link Fragment} subclass.
 */
public class GoodsDetailFragment extends Fragment {


    private ProgressBarWebView webview;
    private Banner banner;
    private ArrayList<String> list = new ArrayList<>();

    private PageContainer container;
     boolean isTopOrBottom = false;

    public GoodsDetailFragment() {
        // Required empty public constructor
        //
    }

    private static GoodsDetailFragment fragment = null;

    public static GoodsDetailFragment newInstance() {
        if (fragment == null) {
            fragment = new GoodsDetailFragment();
        }
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_goods_detail_with_webview, container, false);

        list.add("https://raw.githubusercontent.com/youth5201314/banner/master/app/src/main/res/mipmap-xhdpi/b3.jpg");
        list.add("https://raw.githubusercontent.com/youth5201314/banner/master/app/src/main/res/mipmap-xhdpi/b1.jpg");
        list.add("https://raw.githubusercontent.com/youth5201314/banner/master/app/src/main/res/mipmap-xhdpi/b2.jpg");
        return inflater.inflate(R.layout.fragment_goods_detail_with_webview, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        banner = (Banner) view.findViewById(R.id.banner);
        container = (PageContainer) view.findViewById(R.id.container);
        //设定依赖视图为id是R.id.pageOne的View
        container.setViewID(R.id.pageOne);
        container.setOnPageChanged(new PageBehavior.OnPageChanged() {
            @Override
            public void toTop() {
                isTopOrBottom = false;
                startHandleTopUI();
            }

            @Override
            public void toBottom() {
                isTopOrBottom = true;
                startHandleBottomUI();
            }
        });
        banner.setImages(list).setImageLoader(new GlideImageLoader()).start();
        webview = (ProgressBarWebView) view.findViewById(R.id.mWebView);
        webview.setProgressColors(Color.RED, Color.BLACK, Color.DKGRAY, Color.YELLOW);
        webview.loadUrl("https://github.com/LeWaves");


    }
    private FirstActivity mActivity;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (FirstActivity) context;
    }

    private void startHandleTopUI() {
        Message msg = new Message();
        msg.what=1;
        mActivity.handler.sendMessage(msg);
    }

    private void startHandleBottomUI() {
        Message msg = new Message();
        msg.what=0;
        mActivity.handler.sendMessage(msg);
    }


}
