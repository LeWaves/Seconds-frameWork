package com.lt.integrate.frame.demo.listener;

import android.view.View;

import java.util.List;

/**
 * Created by iclick on 2017/9/26.
 */

public interface HttpsListenerInterface {
    void onSucessHttps(List<Object> list,String data );
    void onErrorHttps(String error );
}
