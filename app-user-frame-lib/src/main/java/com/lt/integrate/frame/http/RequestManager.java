package com.lt.integrate.frame.http;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.lt.integrate.frame.IOkHttpClient;

import okhttp3.OkUrlFactory;

/**
 * Created by iclick on 2017/9/21.
 */

public class RequestManager {
    private static RequestQueue mRequestQueue;

    private RequestManager() {
    }

    public static void init(Context context,String url) {
        if (mRequestQueue == null) {
            if(isHttps(url)) {
                mRequestQueue = Volley.newRequestQueue(context, new OkHttpStack(new OkUrlFactory(IOkHttpClient.getUnsafeOkHttpsClient())));
            }else{
                mRequestQueue = Volley.newRequestQueue(context, new OkHttpStack(new OkUrlFactory(IOkHttpClient.getUnsafeOkHttpClient())));
            }
        }

    }
    /**
     * 检测是否https
     */
    private static boolean isHttps(String url) {
        return url.startsWith("https");
    }

    /**
     * @return instance() of the queue
     * @throws IllegalStateException if init has not yet been called
     */
    public static RequestQueue getRequestQueue() {
        if (mRequestQueue != null) {
            return mRequestQueue;
        } else {
            throw new IllegalStateException("Not initialized");
        }
    }

    /**
     * @return instance() of the queue
     * @throws IllegalStateException if init has not yet been called
     */
    public static RequestQueue getRequestQueueNoThrowable() {
        return mRequestQueue;
    }
}
