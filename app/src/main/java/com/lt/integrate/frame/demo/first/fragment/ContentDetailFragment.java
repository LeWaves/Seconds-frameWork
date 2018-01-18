package com.lt.integrate.frame.demo.first.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lt.integrate.frame.demo.R;
import com.lt.integrate.frame.view.ProgressBarWebView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContentDetailFragment extends Fragment {

    ProgressBarWebView webview;
    public ContentDetailFragment() {
        // Required empty public constructor
    }


    private static ContentDetailFragment fragment = null;

    public static ContentDetailFragment newInstance() {
        if (fragment == null) {
            fragment = new ContentDetailFragment();
        }
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_content_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        webview = (ProgressBarWebView) view.findViewById(R.id.mWebView);
        webview.setProgressColors(Color.RED, Color.BLACK, Color.DKGRAY, Color.YELLOW);
        webview.loadUrl("https://github.com/LeWaves");
    }
}
